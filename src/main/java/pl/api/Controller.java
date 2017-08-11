package pl.api;

import com.mashape.unirest.http.    exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import pl.ProfileService;
import pl.SecurityContextService;
import pl.model.User;
import pl.other.GsonTest;
import pl.spotify.SpotifyGetService;
import pl.spotify.SpotifyLoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/api/spotify/")
@RestController
public class Controller {

    private final SpotifyLoginService spotifyLoginService;
    private final SpotifyGetService spotifyGetService;
    private final SecurityContextService securityContextService;
    private final ProfileService profileService;
    private final GsonTest gsonTest;
    private final SaveMusic saveMusic;

    @Autowired
    public Controller(SpotifyLoginService spotifyLoginService, SpotifyGetService spotifyGetService, SecurityContextService securityContextService, ProfileService profileService, GsonTest gsonTest, SaveMusic saveMusic) {
        this.spotifyLoginService = spotifyLoginService;
        this.spotifyGetService = spotifyGetService;
        this.securityContextService = securityContextService;
        this.profileService = profileService;
        this.gsonTest = gsonTest;
        this.saveMusic = saveMusic;
    }

    @GetMapping("/geturl")
    public String model() throws IOException {
        String url = spotifyLoginService.getSpotifyLoginUrl();
        return url;
//        return new ModelAndView("redirect:" + url);
//        return url;
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl(url);
//        return redirectView;
    }

    //direct !
    @GetMapping("/callback")
    public void callback(HttpServletRequest request) throws IOException, UnirestException {
        String code = request.getQueryString().replace("code=","");
        Cookie[] cookies = request.getCookies();
        String username = cookies[0].getValue();
        String accessToken = spotifyLoginService.getAccessToken(code);
        System.out.println("USERNAME"+username);


        profileService.setSpotifyAccessToken(accessToken, username);
        String topTracks = spotifyGetService.getTopTracks(accessToken);

//   1.     saveMusic.update(topTracks);


        //get username

//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("http://google.com/");
//        return redirectView;
    }

    @GetMapping("/gettoptracks")
    public String getTopTracks() throws IOException, UnirestException {
        String spotifyAccessToken = profileService.getSpotifyAccessToken();
        System.out.print(spotifyAccessToken);
        String topTracks = spotifyGetService.getTopTracks(spotifyAccessToken);
        System.out.println(topTracks);
        gsonTest.gson(topTracks);
        System.out.println("mapped");
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
