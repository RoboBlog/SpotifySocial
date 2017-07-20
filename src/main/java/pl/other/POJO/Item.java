
package pl.other.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;

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


    @OneToMany(cascade=CascadeType.ALL)
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

    public Integer getDiscNumber() {
        return discNumber;
    }

    public void setDiscNumber(Integer discNumber) {
        this.discNumber = discNumber;
    }

//    public Album getAlbum() {
//        return album;
//    }
//
//    public void setAlbum(Album album) {
//        this.album = album;
//    }
//
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public ExternalIds getExternalIds() {
//        return externalIds;
//    }

//    public void setExternalIds(ExternalIds externalIds) {
//        this.externalIds = externalIds;
//    }
//
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getDurationMs() {
        return durationMs;
    }

    public void setDurationMs(Integer durationMs) {
        this.durationMs = durationMs;
    }

    public Boolean getExplicit() {
        return explicit;
    }

    public void setExplicit(Boolean explicit) {
        this.explicit = explicit;
    }

    public Boolean getIsPlayable() {
        return isPlayable;
    }

    public void setIsPlayable(Boolean isPlayable) {
        this.isPlayable = isPlayable;
    }

//    public List<Artist> getArtists() {
//        return artists;
//    }
///
//    public void setArtists(List<Artist> artists) {
//        this.artists = artists;
//    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.trackNumber = trackNumber;
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
//
//    public ExternalUrls__ getExternalUrls() {
//        return externalUrls;
//    }
//
//    public void setExternalUrls(ExternalUrls__ externalUrls) {
//        this.externalUrls = externalUrls;
//    }
//
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "discNumber=" + discNumber +
//                ", album=" + album +
                ", type='" + type + '\'' +
//                ", externalIds=" + externalIds +
                ", uri='" + uri + '\'' +
                ", durationMs=" + durationMs +
                ", explicit=" + explicit +
                ", isPlayable=" + isPlayable +
//                ", artists=" + artists +
                ", previewUrl='" + previewUrl + '\'' +
                ", popularity=" + popularity +
                ", name='" + name + '\'' +
                ", trackNumber=" + trackNumber +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
//                ", externalUrls=" + externalUrls +
                '}';
    }
}
