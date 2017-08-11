package pl.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ProfileService;
import pl.deezer.DeezerLoginService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by maciek on 7/28/17.
 */
@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/api/deezer/")
@RestController
public class Deezer {
    private final DeezerLoginService deezerLoginService;
    private final ProfileService profileService;

    @Autowired
    public Deezer(DeezerLoginService deezerLoginService, ProfileService profileService) {
        this.deezerLoginService = deezerLoginService;
        this.profileService = profileService;
    }

    @GetMapping("/geturl")
    public String getDeezerLoginUrl(){
        String url = deezerLoginService.getDeezerLoginUrl();
        return url;
    }

    @GetMapping("/callback")
    public void callback(HttpServletRequest request) throws IOException {
        String code = request.getQueryString().replace("code=","");

        Cookie[] cookies = request.getCookies();
        String username = cookies[0].getValue();

        String accessToken = deezerLoginService.getAccessToken(code);
        System.out.print(accessToken);
        profileService.setDeezerAccessToken(accessToken, username);
    }
}
