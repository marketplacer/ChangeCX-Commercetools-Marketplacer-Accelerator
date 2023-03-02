
package functions;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Advert {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("brand")
    @Expose
    private Brand brand;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("fullTitle")
    @Expose
    private String fullTitle;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("displayImage")
    @Expose
    private DisplayImage displayImage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
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

}
