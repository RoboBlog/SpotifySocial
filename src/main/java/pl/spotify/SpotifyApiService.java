package pl.spotify;

import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import pl.model.UserRepository;
import pl.spotify.POJO.Spotify;
import pl.spotify.POJO.SpotifyRepository;
import pl.userProfile.ProfileService;
import pl.userProfile.UserService;
import pl.util.HttpClient;

import java.io.IOException;
import java.util.Map;

@Service
public class SpotifyApiService {

    private final ProfileService profileService;
    private final SpotifyRepository spotifyRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final HttpClient httpClient;

    public SpotifyApiService(ProfileService profileService, SpotifyRepository spotifyRepository, UserService userService, UserRepository userRepository, HttpClient httpClient) {
        this.profileService = profileService;
        this.spotifyRepository = spotifyRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.httpClient = httpClient;
    }


    public String getTopTracks(String userAccessToken) throws IOException {
        String url = "https://api.spotify.com/v1/me/top/tracks?limit=50";
        Map<String, String> headers = httpClient.setAuthHeader(userAccessToken);

        return httpClient.get(url, headers);
    }

    public String getRecentlyPlayed(String userAccessToken) throws IOException {
        String url = "https://api.spotify.com/v1/me/player/recently-played?limit=50";
        Map<String, String> headers = httpClient.setAuthHeader(userAccessToken);

        return httpClient.get(url, headers);
    }

    public String getTopArtists(String userAccessToken) throws IOException {
        String url = "https://api.spotify.com/v1/me/top/artists?limit=50";
        Map<String, String> headers = httpClient.setAuthHeader(userAccessToken);

        return httpClient.get(url, headers);
    }

    public void saveTopTracks(String topTracks) {
        Gson gson = new Gson();
        Spotify spotify = gson.fromJson(topTracks, Spotify.class);
        spotify.setUser(userService.authTest());
        System.out.println(spotify);
        spotifyRepository.save(spotify);
    }


    //TODO DELETE THIS
    public void saveTopTracks(String username, String topTracks) {
        Gson gson = new Gson();
        Spotify spotify = gson.fromJson(topTracks, Spotify.class);
        spotify.setUser(userRepository.findByUsername(username));
        System.out.println(spotify);
        spotifyRepository.save(spotify);
    }

}
