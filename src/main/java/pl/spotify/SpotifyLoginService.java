package pl.spotify;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.util.HttpClient;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class SpotifyLoginService {

    private final HttpClient httpClient;

    private final String clientId = "471e3e2552344f56baaae5ecb0752cc8";
    //    private final String redirectURI = System.getenv("SPOTIFY_REDIRECT_URL");
    private final String redirectURI = "http://localhost:9000/spotify/fetch/callback";
    private final String spotifyApiKey = System.getenv("SPOTIFY_API_KEY");

    public SpotifyLoginService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }


    //TODO refactor this?
    public String getSpotifyLoginUrl() throws IOException {
        List<String> scopes = new LinkedList<>();
        scopes.add("user-read-private");
        scopes.add("user-read-email");
        scopes.add("playlist-read-private");
        scopes.add("user-library-read");
        scopes.add("user-top-read");
        scopes.add("user-read-playback-state");
        scopes.add("user-read-recently-played");

        String scopesText = String.join("%20", scopes);

        StringBuilder stringBuilder = new StringBuilder("https://accounts.spotify.com/authorize?response_type=code&client_id=");
        stringBuilder.append(clientId);
        stringBuilder.append("&scope=");
        stringBuilder.append(scopesText);
        stringBuilder.append("&redirect_uri=");
        stringBuilder.append(redirectURI);

        return stringBuilder.toString();
    }

    public String fetchAccessToken(String code) throws IOException {

        String url = "https://accounts.spotify.com/api/token";
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("code", code);
        params.put("redirect_uri", redirectURI);
        params.put("grant_type", "authorization_code");

        Map<String, String> headers = setApiKeyHeader();

        String response = httpClient.post(url, params, headers);

        String accessToken = getAccessTokenFromJson(response);
        return accessToken;
    }


    private Map<String, String> setApiKeyHeader() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("Authorization", spotifyApiKey);
        return headers;
    }

    private String getAccessTokenFromJson(String response) {
        JSONObject responseJson = new JSONObject(response);
        return responseJson.get("access_token").toString();
    }
}

