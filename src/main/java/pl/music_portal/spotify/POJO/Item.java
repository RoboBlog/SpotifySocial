
package pl.music_portal.spotify.POJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class Item {

    @JsonProperty("album")
    public Album album;
    @JsonProperty("artists")
    public List<Artist> artists = null;
    @JsonProperty("disc_number")
    public Long discNumber;
    @JsonProperty("duration_ms")
    public Long durationMs;
    @JsonProperty("explicit")
    public Boolean explicit;
    @JsonProperty("external_ids")
    public ExternalIds externalIds;
    @JsonProperty("external_urls")
    public ExternalUrls__ externalUrls;
    @JsonProperty("href")
    public String href;
    @JsonProperty("id")
    public String id;
    @JsonProperty("is_playable")
    public Boolean isPlayable;
    @JsonProperty("name")
    public String name;
    @JsonProperty("popularity")
    public Long popularity;
    @JsonProperty("preview_url")
    public String previewUrl;
    @JsonProperty("track_number")
    public Long trackNumber;
    @JsonProperty("type")
    public String type;
    @JsonProperty("uri")
    public String uri;
    @JsonProperty("linked_from")
    public LinkedFrom linkedFrom;

}
//@Data
//@Entity
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class Item {
//    @Id
//    @GeneratedValue
//    private int itemId;
//
//    @SerializedName("disc_number")
//    @Expose
//    private Integer discNumber;
//    //    @SerializedName("album")
////    @Expose
////    private Album album;
//    @SerializedName("type")
//    @Expose
//    private String type;
//    //    @SerializedName("external_ids")
////    @Expose
////    private ExternalIds externalIds;
//    @SerializedName("uri")
//    @Expose
//    private String uri;
//    @SerializedName("duration_ms")
//    @Expose
//    private Integer durationMs;
//    @SerializedName("explicit")
//    @Expose
//    private Boolean explicit;
//    @SerializedName("is_playable")
//    @Expose
//    private Boolean isPlayable;
//
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @SerializedName("artists")
//    @Expose
//    private List<Artist> artists = null;
//
//
//    @SerializedName("preview_url")
//    @Expose
//    private String previewUrl;
//    @SerializedName("popularity")
//    @Expose
//    private Integer popularity;
//    @SerializedName("name")
//    @Expose
//    private String name;
//    @SerializedName("track_number")
//    @Expose
//    private Integer trackNumber;
//    @SerializedName("href")
//    @Expose
//    private String href;
//    @SerializedName("id")
//    @Expose
//    private String id;
//    @ManyToOne
//    private Spotify spotify;
//
//
//    //    @SerializedName("external_urls")
////    @Expose
////    private ExternalUrls__ externalUrls;
//}
