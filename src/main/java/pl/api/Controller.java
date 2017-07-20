package pl.api;

import com.mashape.unirest.http.    exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import pl.ProfileService;
import pl.SecurityContextService;
import pl.model.User;
import pl.other.GsonTest;
import pl.spotify.SpotifyGetService;
import pl.spotify.SpotifyLoginService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/spotify/")
@RestController
public class Controller {

    private final SpotifyLoginService spotifyLoginService;
    private final SpotifyGetService spotifyGetService;
    private final SecurityContextService securityContextService;
    private final ProfileService profileService;
    private final GsonTest gsonTest;

    @Autowired
    public Controller(SpotifyLoginService spotifyLoginService, SpotifyGetService spotifyGetService, SecurityContextService securityContextService, ProfileService profileService, GsonTest gsonTest) {
        this.spotifyLoginService = spotifyLoginService;
        this.spotifyGetService = spotifyGetService;
        this.securityContextService = securityContextService;
        this.profileService = profileService;
        this.gsonTest = gsonTest;
    }

    @RequestMapping("/")
    public ModelAndView model() throws IOException {
        String url = spotifyLoginService.getSpotifyLoginUrl();
        return new ModelAndView("redirect:" + url);
    }

    //direct !
    @RequestMapping("/callback")
    public String Callback(HttpServletRequest request) throws IOException, UnirestException {
        String code = request.getQueryString().replace("code=","");
        String accessToken = spotifyLoginService.getAccessToken(code);
        profileService.setSpotifyAccessToken(accessToken);
        return accessToken;

    }
    @RequestMapping("/getTopTracks")
    public String getTopTracks() throws UnirestException {
        String spotifyAccessToken = profileService.getSpotifyAccessToken();
        System.out.println("GetToken");



        String topTracks = spotifyGetService.getTopTracks(spotifyAccessToken);
        System.out.println("Get Array");

//        HttpResponse<JsonNode> topTracks = spotifyGetService.getTopTracks(spotifyGetService.getLoggedUserAccessToken());
//        return topTracks.getBody().toString();
        gsonTest.gson(topTracks);
        System.out.println("mapped");
//        System.out.println(topTrac?ks);
        return "topTracks";
    }

    
    @GetMapping("/testapi")
    public User testapi(){
        User test = new User();
        test.setSpotifyAccessToken("34ut334");
        test.setEnabled(1);
        test.setPassword("sdkfjkm");
        test.setConfirmationId("fdsjgdjkshngjdfshfd432");
        test.setEmail("rwewt@gmail.com");
        test.setUsername("tesw");
    return test;
    }

    @GetMapping("/testapi2")
    public User testapi2(){
        User test = new User();
        test.setSpotifyAccessToken("3gdfsgdsg");
        test.setEnabled(0);
        test.setPassword("sdkfjkm");
        test.setConfirmationId("fdsjgdjkfdsafsadshngjdfshfd432");
        test.setEmail("testapi2@gmail.com");
        test.setUsername("tesw");
    return test;
    }
    @GetMapping("/testlog1")
    public User testlog1(){
        User user = profileService.authTest();
        return user;
    }

    @GetMapping("/authorities")
    public Object getAuthorities(){
        Object authorities = securityContextService.getAuthorities();
        return authorities;
    }
    @GetMapping("/testlog")
    public void testlog(){
        System.out.println(securityContextService.getUsername());
    }
}
