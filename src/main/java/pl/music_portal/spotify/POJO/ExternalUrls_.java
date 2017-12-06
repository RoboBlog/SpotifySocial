
package pl.music_portal.spotify.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ExternalUrls_ {
    @SerializedName("spotify")
    @Expose
    private String spotify;
}
