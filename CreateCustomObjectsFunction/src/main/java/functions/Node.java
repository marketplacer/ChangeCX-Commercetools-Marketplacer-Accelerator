
package functions;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Node {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("logoUrl")
    @Expose
    private String logoUrl;
    @SerializedName("__typename")
    @Expose
    private String typename;
    @SerializedName("businessName")
    @Expose
    private String businessName;
    @SerializedName("emailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("openingHours")
    @Expose
    private Object openingHours;
    @SerializedName("sellerRating")
    @Expose
    private String sellerRating;
    @SerializedName("businessNumber")
    @Expose
    private String businessNumber;
    @SerializedName("youtubeVideoUrl")
    @Expose
    private Object youtubeVideoUrl;
    @SerializedName("storeDescription")
    @Expose
    private Object storeDescription;
    @SerializedName("marketplaceShippingRulesEnabled")
    @Expose
    private Boolean marketplaceShippingRulesEnabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Object getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(Object openingHours) {
        this.openingHours = openingHours;
    }

    public String getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(String sellerRating) {
        this.sellerRating = sellerRating;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public Object getYoutubeVideoUrl() {
        return youtubeVideoUrl;
    }

    public void setYoutubeVideoUrl(Object youtubeVideoUrl) {
        this.youtubeVideoUrl = youtubeVideoUrl;
    }

    public Object getStoreDescription() {
        return storeDescription;
    }

    public void setStoreDescription(Object storeDescription) {
        this.storeDescription = storeDescription;
    }

    public Boolean getMarketplaceShippingRulesEnabled() {
        return marketplaceShippingRulesEnabled;
    }

    public void setMarketplaceShippingRulesEnabled(Boolean marketplaceShippingRulesEnabled) {
        this.marketplaceShippingRulesEnabled = marketplaceShippingRulesEnabled;
    }

}
