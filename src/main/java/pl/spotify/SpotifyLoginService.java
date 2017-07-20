package pl.spotify;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SpotifyLoginService {
    private String clientId = "471e3e2552344f56baaae5ecb0752cc8";
    private String redirectURI = "http://localhost:8080/spotify/callback";



    public String getSpotifyLoginUrl() throws IOException {
        String url = "https://accounts.spotify.com/authorize?response_type=code&client_id="+clientId+"&scope=user-read-private%20user-read-email%20playlist-read-private%20user-library-read%20user-top-read&redirect_uri=" + redirectURI;
        return url;
    }

    public String getAccessToken(String code) throws UnirestException {

        HttpResponse<JsonNode> jsonResponse= Unirest.post("https://accounts.spotify.com/api/token")
                .header("Authorization", "<token>")
                .field("code", code)
                .field("redirect_uri", redirectURI)
                .field("grant_type", "authorization_code")
                .asJson();

        String accessToken = jsonResponse.getBody().getObject().getString("access_token");
        return accessToken;
    }

}

