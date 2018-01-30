package pl.music_portal.spotify.POJO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExternalUrls___ {

    @JsonProperty("spotify")
    public String spotify;
}
