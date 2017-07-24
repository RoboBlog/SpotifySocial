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
//    private String redirectURI = "http://localhost:8080/api/spotify/callback";
    private String redirectURI = "http://109.207.104.156:8080/api/spotify/callback";



    public String getSpotifyLoginUrl() throws IOException {
        String url = "https://accounts.spotify.com/authorize?response_type=code&client_id="+clientId+"&scope=user-read-private%20user-read-email%20playlist-read-private%20user-library-read%20user-top-read&redirect_uri=" + redirectURI;
        return url;
    }

    public String getAccessToken(String code) throws UnirestException {

        HttpResponse<JsonNode> jsonResponse= Unirest.post("https://accounts.spotify.com/api/token")
                .header("Authorization", "Basic NDcxZTNlMjU1MjM0NGY1NmJhYWFlNWVjYjA3NTJjYzg6ODI5NTViMjI1Y2NiNGJlYmE0YWIzNDQ5ZmRmNjhhMmU=")
                .field("code", code)
                .field("redirect_uri", redirectURI)
                .field("grant_type", "authorization_code")
                .asJson();

        String accessToken = jsonResponse.getBody().getObject().getString("access_token");
        return accessToken;
    }

}

