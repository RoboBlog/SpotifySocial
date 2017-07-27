package pl.spotify;

import com.google.gson.JsonElement;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.ProfileService;
import pl.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class SpotifyGetService {

    private final ProfileService profileService;

    public SpotifyGetService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public String getTopTracks(String userAccessToken) throws IOException {
        URL url = new URL("https://api.spotify.com/v1/me/top/tracks");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + userAccessToken);
        conn.setDoOutput(true);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String response = sb.toString();
//        System.out.print(response);
        return response;
    }


    public String getTopArtists(String userAccessToken) throws IOException {
        URL url = new URL("https://api.spotify.com/v1/me/top/artists");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + userAccessToken);
        conn.setDoOutput(true);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String response = sb.toString();
//        System.out.print(response);
        return response;

    }
    public String getLoggedUserAccessToken(){
        //if user is logged
        User user = profileService.authTest();
        return user.getSpotifyAccessToken();
    }
}
