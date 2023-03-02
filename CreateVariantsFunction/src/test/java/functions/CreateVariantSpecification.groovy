package functions

import com.commercetools.api.models.product.Product
import spock.lang.Specification

class CreateVariantSpecification extends Specification {

    CreateVariant testObject = new CreateVariant()

    MarketplacerRequest request =  new MarketplacerRequest()
    Payload payload = new Payload()
    Data data = new Data()
    Node node = new Node()
    Variants variant = new Variants()
    Edge edges = new Edge()
    Node innerNode = new Node()
    DisplayImage displayImage = new DisplayImage()
    Random random = new Random()
    OptionValues values = new OptionValues()
    OptionType sizeOption =  new OptionType()
    Option sizeValue = new Option()

    def setup() {
        request.setPayload(payload)
        payload.setData(data)
        data.setNode(node)
        node.setVariants(variant)
        values.setNodes([sizeValue])
        sizeValue.setOptionType(sizeOption)
        sizeOption.setName("Size")
        sizeValue.setName("1")
        node.setDescription("Some Description")
        variant.setEdges([edges])
        edges.setNode(innerNode)
        innerNode.setLowestPrice("199.99")
        innerNode.setOptionValues(values)
        displayImage.setUrl("https://marketplacer.imgix.net/1m/Bq7bkGpTkCi_RVyD48ah0QtRY.png?auto=format&fm=pjpg&fit=max&w=&h=&s=924a01d036b45da285cad64137bcdf85")
        node.setDisplayImage(displayImage)

    }

    def "should create a product with valid attributes" () {
        given:

        def nodeId = "prd" + random.nextInt()
        def innerNodeId = "skuId" +random.nextInt()
        def prodTitle = "Sample Product created with Spock Test " + nodeId
        node.setId(nodeId)
        node.setLegacyId(nodeId)
        node.setTitle(prodTitle)
        innerNode.setId(innerNodeId)

        when:
        Product prod = testObject.createProduct(request)

        then:
        prod.getKey() == nodeId
    }

    def "should update an existing product" () {
        given:
        Random random = new Random()
        def nodeId = "prd-422768855"
        def productTitle = "Sample Product created with Spock Test " + random.nextInt()
        node.setId(nodeId)
        node.setTitle(productTitle)
        Optional<Product> productToUpdate = testObject.getProductByKey(request.getPayload().getData().getNode().getId());

        when:
        Product prod = testObject.updateProduct(productToUpdate.get(), request)

        then:
        prod.getKey() == nodeId
    }

}