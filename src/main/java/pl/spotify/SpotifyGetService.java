package pl.spotify;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

/**
 * Created by maciek on 7/8/17.
 */
@Service
public class SpotifyGetService {

    public HttpResponse<JsonNode> getTopTracks(String userAccessToken) throws UnirestException {
        HttpResponse<JsonNode> jsonTopTracks = Unirest.get("https://api.spotify.com/v1/me/top/tracks")
                .header("Authorization", "Bearer " + userAccessToken).asJson();
        return jsonTopTracks;
    }

    public HttpResponse<JsonNode> getTopArtists(String userAccessToken) throws UnirestException{
        HttpResponse<JsonNode> jsonTopArtists= Unirest.get("https://api.spotify.com/v1/me/top/artists")
                .header("Authorization", "Bearer " + userAccessToken).asJson();
        return jsonTopArtists;

    }
}
