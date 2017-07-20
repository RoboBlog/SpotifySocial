
package pl.other.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pl.model.User;

import javax.persistence.*;

@Entity
public class Artist {

    @Id
    @GeneratedValue
    private int artistId;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
//    @SerializedName("external_urls")
//    @Expose
//    private ExternalUrls_ externalUrls;
    @SerializedName("uri")
    @Expose
    private String uri;
    @ManyToOne
    private Item item;

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public ExternalUrls_ getExternalUrls() {
//        return externalUrls;
//    }
//
//    public void setExternalUrls(ExternalUrls_ externalUrls) {
//        this.externalUrls = externalUrls;
//    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
//                ", externalUrls=" + externalUrls +
                ", uri='" + uri + '\'' +
                '}';
    }
}
