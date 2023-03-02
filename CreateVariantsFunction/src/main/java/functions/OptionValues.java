package functions;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class OptionValues {

    @SerializedName("nodes")
    @Expose
    private List<Option> nodes = null;

    public List<Option> getNodes() {
        return nodes;
    }

    public void setNodes(List<Option> nodes) {
        this.nodes = nodes;
    }

}