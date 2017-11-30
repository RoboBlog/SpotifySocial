package pl.api;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pl.spotify.POJO.Item;
import pl.spotify.POJO.Spotify;
import pl.spotify.SpotifyApiService;
import pl.spotify.TrackDto;
import pl.userProfile.ProfileService;
import pl.spotify.SpotifyDataService;
import pl.spotify.SpotifyLoginService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RequestMapping("/api/spotify/")
@RestController
public class SpotifyController {

    private final ProfileService profileService;
    private final SpotifyApiService spotifyApiService;
    private final SpotifyLoginService spotifyLoginService;
    private final SpotifyDataService spotifyDataService;

    @Autowired
    public SpotifyController(ProfileService profileService, SpotifyApiService spotifyApiService, SpotifyLoginService spotifyLoginService, SpotifyDataService spotifyDataService) {
        this.profileService = profileService;
        this.spotifyApiService = spotifyApiService;
        this.spotifyLoginService = spotifyLoginService;
        this.spotifyDataService = spotifyDataService;
    }

    @GetMapping("/add")
    public RedirectView model() throws IOException {
        String url = spotifyLoginService.getSpotifyLoginUrl();
        RedirectView redirectView = new RedirectView(url);
        return redirectView;
    }

//    @GetMapping("/callback")
//    public RedirectView callback(HttpServletRequest request, @CookieValue("login") String login) throws IOException {
//        String code = request.getQueryString().replace("code=","");
//        System.out.print(login);
//        String username = "maciek";
//        String accessToken = spotifyLoginService.getAccessToken(code);
//        System.out.println("USERNAME"+username);
//        profileService.setSpotifyAccessToken(accessToken, username);
//        //FIX
//        String topTracks = spotifyGetService.getTopTracks(accessToken);
//        spotifyGetService.saveTopTracks(username, topTracks);
//
//        String url = "http://localhost:8000/musicportalstatus"; //TODO FIX IT
//        RedirectView redirectView = new RedirectView(url);
//        return redirectView;
//    }


    @GetMapping("/callback")
    public List<String> callback(HttpServletRequest request) throws IOException {
        String code = request.getQueryString().replace("code=","");
        String username = "maciek";
        String accessToken = spotifyLoginService.fetchAccessToken(code);
        System.out.println("USERNAME"+username);
//        profileService.setSpotifyAccessToken(accessToken, username);
        //FIX
        String topTracks = spotifyApiService.getTopTracks(accessToken);

        Gson gson = new Gson();
        Spotify spotify = gson.fromJson(topTracks, Spotify.class);
        List<Item> items = spotify.getItems();

        List<String> names = new LinkedList<>();

        items.forEach(item->{
            names.add(item.getName());
        });
//        String url = "http://localhost:8080/musicportalstatus"; //TODO FIX IT
//        RedirectView redirectView = new RedirectView(url);
        return names;
    }

//    //NOPEEEE
//    @GetMapping("/gettoptracks")
//    public String getTopTracks() throws IOException {
//        String topTracks = spotifyGetService.getTopTracks();
//        spotifyGetService.saveTopTracks(topTracks);
//        return "topTracks";
//    }

    @RequestMapping("/getfavouritemusicgenres")
    public List<String> getAll() throws IOException {
        List<String> favoriteMusicGenres = spotifyDataService.getFavoriteMusicGenres();
        return favoriteMusicGenres;
    }

    //SERVICE
    @GetMapping("/gettoptrackslist")
    public List<TrackDto> getTopTracksList(){
        List<Item> items = spotifyDataService.getItems();

        List<TrackDto> tracksList = new LinkedList<>();

        items.forEach(item -> {
            TrackDto track = new TrackDto(item.getArtists().get(0).getName(),item.getName(), item.getId());
            tracksList.add(track);
        });

        return tracksList;
    }

//    @GetMapping("/getnewreleases")
//    public String getNewReleases(){
//        https://api.spotify.com/v1/browse/new-releases
//    }

}
