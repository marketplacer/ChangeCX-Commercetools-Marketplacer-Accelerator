package functions;

import com.commercetools.api.client.ProjectApiRoot;
import com.commercetools.api.defaultconfig.ApiRootBuilder;
import com.commercetools.api.defaultconfig.ServiceRegion;
import com.commercetools.api.models.common.Image;
import com.commercetools.api.models.common.PriceDraft;
import com.commercetools.api.models.product.*;
import com.google.cloud.functions.HttpFunction;
import com.google.cloud.functions.HttpRequest;
import com.google.cloud.functions.HttpResponse;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.vrap.rmf.base.client.error.NotFoundException;
import io.vrap.rmf.base.client.oauth2.ClientCredentials;
import org.apache.commons.lang3.exception.ExceptionUtils;
import com.commercetools.api.models.common.Reference;
import com.commercetools.api.models.common.ReferenceBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;


public class CreateVariant implements HttpFunction {
    private static final Gson gson = new Gson();

    private static final Logger logger = Logger.getLogger(CreateVariant.class.getName());

    private static final ConfigReader configReader = new ConfigReader();

    public static ProjectApiRoot createApiClient() {
        final ProjectApiRoot apiRoot = ApiRootBuilder.of()
                .defaultClient(ClientCredentials.of()
                                .withClientId(configReader.getClientId())
                                .withClientSecret(configReader.getSecret())
                                .build(),
                        ServiceRegion.GCP_US_CENTRAL1)
                .build(configReader.getProjectId());
        return apiRoot;
    }

    static ProjectApiRoot apiRoot = CreateVariant.createApiClient();

    @Override
    public void service(HttpRequest request, HttpResponse response)
            throws IOException {
        BufferedWriter writer = response.getWriter();
        String requestBody = CharStreams.toString(request.getReader());
        logger.info("Request : " + requestBody);
        MarketplacerRequest marketplacerRequest = gson.fromJson(requestBody, MarketplacerRequest.class);
        try {
            logger.info(gson.toJson(marketplacerRequest));
            JsonObject jsonResponse = new JsonObject();
            Optional<Product> product = getProductByKey(marketplacerRequest.getPayload().getData().getNode().getLegacyId());
            if (!product.isPresent()) {
                String productId = createProduct(marketplacerRequest).getId();
                jsonResponse.addProperty("productId", productId);
                writer.write(gson.toJson(jsonResponse));
                logger.info("Product created: " + productId);
            } else {
                updateProduct(product.get(), marketplacerRequest);
                jsonResponse.addProperty("updatedProduct", product.get().getId());
                writer.write(gson.toJson(jsonResponse));
            }
        } catch (Exception e) {
            String stacktrace = ExceptionUtils.getStackTrace(e);
            JsonObject jsonResponse = new JsonObject();
            jsonResponse.addProperty("originalRequest" , requestBody);
            jsonResponse.addProperty("stackTrace" , stacktrace);
            logger.info(stacktrace);
            writer.write(gson.toJson(jsonResponse));
        }
        response.setContentType("application/json");
    }

    public static Product createProduct(MarketplacerRequest marketplacerRequest) {
        List<ProductVariantDraft> variants = createProductVariantDraft(marketplacerRequest);
        ProductVariantDraft master = variants.get(0);
        variants.remove(0);
        final Reference categoryReference = ReferenceBuilder.of().categoryBuilder().id(configReader.getRootCategory()).build();
        final Reference childCat = ReferenceBuilder.of().categoryBuilder().id(configReader.getChildCategory()).build();
        List categories = new ArrayList();
        categories.add(categoryReference);
        categories.add(childCat);
        ProductDraft newProductDetails = ProductDraft
                .builder()
                .name(stringBuilder ->
                        stringBuilder
                                .addValue("en", marketplacerRequest.getPayload().getData().getNode().getTitle())

                )
                .productType(typeBuilder -> typeBuilder.key(configReader.getMainProductType()))
                .key(marketplacerRequest.getPayload().getData().getNode().getLegacyId())
                .slug(stringBuilder ->
                        stringBuilder
                                .addValue("en", "prod" + marketplacerRequest.getPayload().getData().getNode().getTitle().toLowerCase().replace(" ", "-").replace("'",""))
                )
                .description(stringBuilder ->
                        stringBuilder
                                .addValue("en", marketplacerRequest.getPayload().getData().getNode().getDescription())
                )
                .categories(categories)
                .masterVariant(master)
                .variants(variants)
                .build();

        Product product = apiRoot
                .products()
                .post(newProductDetails)
                .executeBlocking()
                .getBody();

        return product;
    }

    public static List<ProductVariantDraft> createProductVariantDraft(MarketplacerRequest marketplacerRequest) {
        List<ProductVariantDraft> variantsDraft = new ArrayList<>();
        for (Edge variant : marketplacerRequest.getPayload().getData().getNode().getVariants().getEdges()) {
            List<Option> options = variant.getNode().getOptionValues().getNodes();
            Optional<Option> size = options.stream().filter(option -> option.getOptionType().getName().contains("Size")).findFirst() ;
            String sizeValue = size.isPresent() ? size.get().getName() :"1";
            ProductVariantDraft newProductVariantDetails = ProductVariantDraft
                    .builder()
                    .sku(variant.getNode().getSku())
                    .key(variant.getNode().getSku())
                    .prices(createPriceDraft(variant))
                    .images(createImages(marketplacerRequest))
                    .plusAttributes(attributeBuilder -> attributeBuilder.name("size").value(sizeValue))
                    .build();
            variantsDraft.add(newProductVariantDetails);
        }
        return variantsDraft;
    }

    public static PriceDraft createPriceDraft(Edge variant) {
        PriceDraft priceDraft = PriceDraft.builder()
                .value(moneyBuilder -> moneyBuilder.currencyCode("USD").centAmount((long)Double.parseDouble(variant.getNode().getLowestPrice())*100))
                .build();
        return priceDraft;
    }

    public static List<Image> createImages(MarketplacerRequest marketplacerRequest) {
        List<Image> images = new ArrayList<>();
        Image image = Image.builder().dimensions(imageDimensionsBuilder -> imageDimensionsBuilder.h(500).w(500)).url(marketplacerRequest.getPayload().getData().getNode().getDisplayImage().getUrl())
                .build();
        images.add(image);
        return images;
    }

    public static Optional<Product> getProductByKey(String key) {
        Optional<Product> product = Optional.empty();
        try {
            product = Optional.of(apiRoot
                    .products()
                    .withKey(key)
                    .get()
                    .executeBlocking()
                    .getBody());
            logger.info("Product found: " + product.get().getId());
        } catch (NotFoundException e) {
            logger.info("Product " + key + " does not exist yet.");
        }
        return product;
    }

    public static Product updateProduct(Product productToUpdate, MarketplacerRequest marketplacerRequest) {
        Product updatedProduct = null;
        for (int i=0; i<productToUpdate.getMasterData().getCurrent().getVariants().size(); i++) {
            ProductVariant variant = productToUpdate.getMasterData().getCurrent().getVariants().get(i);
            final int index = i;
            ProductUpdate productUpdate = ProductUpdate
                    .builder()
                    .version(productToUpdate.getVersion())
                    .plusActions(actionBuilder ->
                            actionBuilder.changeNameBuilder()
                                    .name(stringBuilder ->
                                            stringBuilder
                                                    .addValue("en", marketplacerRequest.getPayload().getData().getNode().getTitle())))
                    .plusActions(actionBuilder -> actionBuilder.changePriceBuilder()
                            .priceId(variant.getPrices().get(0).getId())
                            .price(PriceDraft.builder()
                                    .value(moneyBuilder -> moneyBuilder.currencyCode("USD").centAmount((long) Double.parseDouble(marketplacerRequest.getPayload().getData().getNode().getVariants().getEdges().get(index+1).getNode().getLowestPrice())*100))
                                    .build()))
                    .build();

            updatedProduct = apiRoot
                    .products()
                    .withId(productToUpdate.getId())
                    .post(productUpdate)
                    .executeBlocking()
                    .getBody();

            String updatedProductKey = updatedProduct.getKey();
            System.out.println(updatedProductKey);

        }

        return updateMasterVariant(productToUpdate, marketplacerRequest);
    }

    public static Product updateMasterVariant(Product productToUpdate, MarketplacerRequest marketplacerRequest) {
        ProductUpdate productUpdate = ProductUpdate
                    .builder()
                    .version(productToUpdate.getVersion())
                    .plusActions(actionBuilder ->
                            actionBuilder.changeNameBuilder()
                                    .name(stringBuilder ->
                                            stringBuilder
                                                    .addValue("en", marketplacerRequest.getPayload().getData().getNode().getTitle())))
                    .plusActions(actionBuilder -> actionBuilder.changePriceBuilder()
                            .priceId(productToUpdate.getMasterData().getCurrent().getMasterVariant().getPrices().get(0).getId())
                            .price(PriceDraft.builder()
                                    .value(moneyBuilder -> moneyBuilder.currencyCode("USD").centAmount((long) Double.parseDouble(marketplacerRequest.getPayload().getData().getNode().getVariants().getEdges().get(0).getNode().getLowestPrice())*100))
                                    .build()))
                    .build();

            Product updatedProduct = apiRoot
                    .products()
                    .withId(productToUpdate.getId())
                    .post(productUpdate)
                    .executeBlocking()
                    .getBody();

            String updatedProductKey = updatedProduct.getKey();
            System.out.println(updatedProductKey);

        return updatedProduct;
    }

}
