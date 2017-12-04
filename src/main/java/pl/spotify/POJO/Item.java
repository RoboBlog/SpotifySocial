
package pl.spotify.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Item {
    @Id
    @GeneratedValue
    private int itemId;

    @SerializedName("disc_number")
    @Expose
    private Integer discNumber;
    //    @SerializedName("album")
//    @Expose
//    private Album album;
    @SerializedName("type")
    @Expose
    private String type;
    //    @SerializedName("external_ids")
//    @Expose
//    private ExternalIds externalIds;
    @SerializedName("uri")
    @Expose
    private String uri;
    @SerializedName("duration_ms")
    @Expose
    private Integer durationMs;
    @SerializedName("explicit")
    @Expose
    private Boolean explicit;
    @SerializedName("is_playable")
    @Expose
    private Boolean isPlayable;


    @OneToMany(cascade = CascadeType.ALL)
    @SerializedName("artists")
    @Expose
    private List<Artist> artists = null;


    @SerializedName("preview_url")
    @Expose
    private String previewUrl;
    @SerializedName("popularity")
    @Expose
    private Integer popularity;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("track_number")
    @Expose
    private Integer trackNumber;
    @SerializedName("href")
    @Expose
    private String href;
    @SerializedName("id")
    @Expose
    private String id;
    @ManyToOne
    private Spotify spotify;


    //    @SerializedName("external_urls")
//    @Expose
//    private ExternalUrls__ externalUrls;
}
