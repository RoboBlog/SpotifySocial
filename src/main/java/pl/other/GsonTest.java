package pl.other;


import com.google.gson.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ProfileService;
import pl.other.POJO.Spotify;
import pl.other.POJO.SpotifyRepository;

/**
 * Created by maciek on 7/14/17.
 */
@Service
public class GsonTest {
    private final SpotifyRepository spotifyRepository;
    private final ProfileService profileService;

    @Autowired
    public GsonTest(SpotifyRepository spotifyRepository, ProfileService profileService) {
        this.spotifyRepository = spotifyRepository;
        this.profileService = profileService;
    }

    public void gson(String json){
    System.out.println("JOSN" + json);
        Gson gson = new Gson();
       Spotify spotify = gson.fromJson(json, Spotify.class);
       spotify.setUser(profileService.authTest());

    System.out.println(spotify);
    spotifyRepository.save(spotify);

    }
}
