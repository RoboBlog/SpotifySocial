package pl.spotify;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class SpotifyLoginService {
    private String clientId = "471e3e2552344f56baaae5ecb0752cc8";
    private final String redirectURI = System.getenv("SPOTIFY_REDIRECT_URL");
    private final String spotifyApiKey = System.getenv("SPOTIFY_API_KEY");



    public String getSpotifyLoginUrl() throws IOException {
        String url = "https://accounts.spotify.com/authorize?response_type=code&client_id="+clientId+"&scope=user-read-private%20user-read-email%20playlist-read-private%20user-library-read%20user-top-read&redirect_uri=" + redirectURI;
        return url;
    }

    public String getAccessToken(String code) throws IOException {

        URL url = new URL("https://accounts.spotify.com/api/token");
        Map<String,Object> params = new LinkedHashMap<>();
        params.put("code", code);
        params.put("redirect_uri", redirectURI);
        params.put("grant_type", "authorization_code");


        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", spotifyApiKey);
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String response = sb.toString();

        JSONObject responseJson = new JSONObject(response);
        String accessToken = responseJson.get("access_token").toString();
//        System.out.print("SPOTIFYLOGINSEVICE"+accessToken);
        return accessToken;
    }

}

