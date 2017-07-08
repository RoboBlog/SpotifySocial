package pl.api;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pl.ProfileService;
import pl.spotify.SpotifyGetService;
import pl.spotify.SpotifyLoginService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by maciek on 07.07.17.
 */
@RequestMapping("/spotify/")
@RestController
public class Controller {
   private final SpotifyLoginService spotifyLoginService;
    private final SpotifyGetService spotifyGetService;
    private final ProfileService profileService;
    @Autowired
    public Controller(SpotifyLoginService spotifyLoginService, SpotifyGetService spotifyGetService, ProfileService profileService) {
        this.spotifyLoginService = spotifyLoginService;
        this.spotifyGetService = spotifyGetService;
        this.profileService = profileService;
    }

    @RequestMapping("/")
    public ModelAndView model() throws IOException {
        String url = spotifyLoginService.getSpotifyLoginUrl();
        return new ModelAndView("redirect:" + url);
    }

    //UserOnly
    @RequestMapping("/callback")
    public String Callback(HttpServletRequest request) throws IOException, UnirestException {
        String code = request.getQueryString().replace("code=","");
        profileService.setSpotifyAccessToken(spotifyLoginService.getAccessToken(code));

        return "Your access token is saved";
    }
    @RequestMapping("/getTopTracks")
    public HttpResponse<JsonNode> getTopTracks() throws UnirestException {
        HttpResponse<JsonNode> topTracks = spotifyGetService.getTopTracks(profileService.getSpotifyAccessToken());
        return topTracks;
    }
}