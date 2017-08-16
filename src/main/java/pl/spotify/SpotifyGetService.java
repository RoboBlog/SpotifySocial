package pl.spotify;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.model.UserRepository;
import pl.userProfile.ProfileService;
import pl.model.User;
import pl.spotify.POJO.Spotify;
import pl.spotify.POJO.SpotifyRepository;
import pl.userProfile.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;

@Service
public class SpotifyGetService {

    private final ProfileService profileService;
    private final SpotifyRepository spotifyRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public SpotifyGetService(ProfileService profileService, SpotifyRepository spotifyRepository, UserService userService, UserRepository userRepository) {
        this.profileService = profileService;
        this.spotifyRepository = spotifyRepository;
        this.userService = userService;
        this.userRepository = userRepository;
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


    public String getTopTracks() throws IOException {
        String userAccessToken = profileService.getSpotifyAccessToken();
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

    public void saveTopTracks(String topTracks){
        Gson gson = new Gson();
        Spotify spotify = gson.fromJson(topTracks, Spotify.class);
        spotify.setUser(userService.authTest());
        System.out.println(spotify);
        spotifyRepository.save(spotify);
    }

    public void saveTopTracks(String username, String topTracks){
        Gson gson = new Gson();
        Spotify spotify = gson.fromJson(topTracks, Spotify.class);
        spotify.setUser(userRepository.findByUsername(username));
        System.out.println(spotify);
        spotifyRepository.save(spotify);
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
        User user = userService.authTest();
        return user.getSpotifyAccessToken();
    }
}
