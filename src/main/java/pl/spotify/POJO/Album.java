
package pl.spotify.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Album {

    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("album_type")
    @Expose
    private String albumType;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("external_urls")
    @Expose
    private ExternalUrls externalUrls;
    @SerializedName("uri")
    @Expose
    private String uri;

    @Override
    public String toString() {
        return "Album{" +
                "images=" + images +
                ", name='" + name + '\'' +
                ", albumType='" + albumType + '\'' +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", externalUrls=" + externalUrls +
                ", uri='" + uri + '\'' +
                '}';
    }
}
