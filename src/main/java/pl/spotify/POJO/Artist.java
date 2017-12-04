
package pl.spotify.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
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

}
