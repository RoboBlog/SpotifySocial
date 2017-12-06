
package pl.music_portal.spotify.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ExternalIds {

    @SerializedName("isrc")
    @Expose
    private String isrc;
}
