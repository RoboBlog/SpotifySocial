package pl.spotify;

import com.google.gson.JsonElement;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.springframework.stereotype.Service;
import pl.ProfileService;
import pl.model.User;

@Service
public class SpotifyGetService {

    private final ProfileService profileService;

    public SpotifyGetService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public String getTopTracks(String userAccessToken) throws UnirestException {
        HttpResponse<JsonNode> jsonTopTracks = Unirest.get("https://api.spotify.com/v1/me/top/tracks")
                .header("Authorization", "Bearer " + userAccessToken).asJson();
        String body = jsonTopTracks.getBody().toString();
        return body;
    }


    public HttpResponse<JsonNode> getTopArtists(String userAccessToken) throws UnirestException{
        HttpResponse<JsonNode> jsonTopArtists= Unirest.get("https://api.spotify.com/v1/me/top/artists")
                .header("Authorization", "Bearer " + userAccessToken).asJson();
        return jsonTopArtists;

    }
    public String getLoggedUserAccessToken(){
        //if user is logged
        User user = profileService.authTest();
        return user.getSpotifyAccessToken();
    }
}
