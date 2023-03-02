
package functions;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Node {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("legacyId")
    @Expose
    private String legacyId;
    @SerializedName("brand")
    @Expose
    private Brand brand;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("seller")
    @Expose
    private Seller seller;
    @SerializedName("variants")
    @Expose
    private Variants variants;
    @SerializedName("fullTitle")
    @Expose
    private String fullTitle;
    @SerializedName("__typename")
    @Expose
    private String typename;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("displayImage")
    @Expose
    private DisplayImage displayImage;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("advert")
    @Expose
    private Advert advert;
    @SerializedName("barcode")
    @Expose
    private Object barcode;
    @SerializedName("countOnHand")
    @Expose
    private Integer countOnHand;
    @SerializedName("lowestPrice")
    @Expose
    private String lowestPrice;
    @SerializedName("optionValues")
    @Expose
    private OptionValues optionValues;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getLegacyId() {
        return legacyId;
    }

    public void setLegacyId(String legacyId) {
        this.legacyId = legacyId;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DisplayImage getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(DisplayImage displayImage) {
        this.displayImage = displayImage;
    }
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public Object getBarcode() {
        return barcode;
    }

    public void setBarcode(Object barcode) {
        this.barcode = barcode;
    }

    public Integer getCountOnHand() {
        return countOnHand;
    }

    public void setCountOnHand(Integer countOnHand) {
        this.countOnHand = countOnHand;
    }

    public String getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(String lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public OptionValues getOptionValues() {
        return optionValues;
    }

    public void setOptionValues(OptionValues optionValues) {
        this.optionValues = optionValues;
    }

}
