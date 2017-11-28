package pl.fetchdata;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.spotify.SpotifyGetService;
import pl.spotify.SpotifyLoginService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequestMapping("/spotify/fetch/")
@ResponseBody
public class FetchdataController {

    private final SpotifyLoginService spotifyLoginService;
    private final SpotifyGetService spotifyGetService;

    public FetchdataController(SpotifyLoginService spotifyLoginService, SpotifyGetService spotifyGetService) {
        this.spotifyLoginService = spotifyLoginService;
        this.spotifyGetService = spotifyGetService;
    }


    @RequestMapping("/get/url")
    public String getSpotifyLoginUrl() throws IOException {
        return spotifyLoginService.getSpotifyLoginUrl();
    }

    @RequestMapping("/callback")
    public String getAccessTokenAndSongs(HttpServletRequest request) throws IOException {
        String code = request.getQueryString().replace("code=","");
        String accessToken = spotifyLoginService.getAccessToken(code);

        String topTracks = spotifyGetService.getTopTracks(accessToken);
        String topArtists = spotifyGetService.getTopArtists(accessToken);
        //TODO get recent music

        //Map to json & save in txt file
        return "It works";
    }
}
