
package pl.spotify.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Image {
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("height")
    @Expose
    private Integer height;
}
