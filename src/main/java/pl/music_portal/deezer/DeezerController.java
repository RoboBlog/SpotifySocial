package pl.music_portal.deezer;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.user.ProfileService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequestMapping("${ver}/deezer/")
@RestController
public class DeezerController {
    private final DeezerLoginService deezerLoginService;
    private final ProfileService profileService;

    public DeezerController(DeezerLoginService deezerLoginService, ProfileService profileService) {
        this.deezerLoginService = deezerLoginService;
        this.profileService = profileService;
    }

    @GetMapping("/geturl")
    public String getDeezerLoginUrl() {
        String url = deezerLoginService.getDeezerLoginUrl();
        return url;
    }

    @GetMapping("/callback")
    public void callback(HttpServletRequest request,
                         @CookieValue("login") String login) throws IOException {
        String code = request.getQueryString().replace("code=", "");

        Cookie[] cookies = request.getCookies();
        String username = cookies[0].getValue();
        System.out.print(login);

        String accessToken = deezerLoginService.getAccessToken(code);
        System.out.print(accessToken);
        profileService.setDeezerAccessToken(accessToken, username);
    }
}
