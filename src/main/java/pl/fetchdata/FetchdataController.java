package pl.fetchdata;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.music_portal.spotify.POJO.Item;
import pl.music_portal.spotify.POJO.Spotify;
import pl.music_portal.spotify.SpotifyApiService;
import pl.music_portal.spotify.SpotifyDataService;
import pl.music_portal.spotify.SpotifyLoginService;
import pl.music_portal.spotify.SpotifySongInfoService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//TODO it is test...not production code
@RequestMapping("${ver}/spotify/fetch/")
@RestController
public class FetchdataController {

    private final SpotifyLoginService spotifyLoginService;
    private final SpotifyApiService spotifyApiService;
    private final SpotifyDataService spotifyDataService;
    private final SpotifySongInfoService spotifySongInfoService;

    public FetchdataController(SpotifyLoginService spotifyLoginService, SpotifyApiService spotifyGetService, SpotifyDataService spotifyDataService, SpotifySongInfoService spotifySongInfoService) {
        this.spotifyLoginService = spotifyLoginService;
        this.spotifyApiService = spotifyGetService;
        this.spotifyDataService = spotifyDataService;
        this.spotifySongInfoService = spotifySongInfoService;
    }


    @GetMapping("/get/url")
    public String getSpotifyLoginUrl() throws IOException {
        return spotifyLoginService.getSpotifyLoginUrl();
    }

    @RequestMapping("/callback")
    public Spotify getAccessTokenAndSongs(HttpServletRequest request) throws IOException {
        String code = request.getQueryString().replace("code=", "");
        String accessToken = spotifyLoginService.fetchAccessToken(code);

        String topTracks = spotifyApiService.getTopTracks(accessToken);
        String topArtists = spotifyApiService.getTopArtists(accessToken);
        String recentlyPlayed = spotifyApiService.getRecentlyPlayed(accessToken);

        LocalDateTime now = LocalDateTime.now();
        try (PrintWriter topArtistsFile = new PrintWriter(now + " - topArtists.txt")) {
            topArtistsFile.println(topArtists);
        }

        try (PrintWriter topTracksFile = new PrintWriter(now + " - topTracks.txt")) {
            topTracksFile.println(topTracks);
        }

        try (PrintWriter topTracksFile = new PrintWriter(now + " - recentlyPlayed.txt")) {
            topTracksFile.println(recentlyPlayed);
        }


        Gson gson = new Gson();
        Spotify spotify = gson.fromJson(topTracks, Spotify.class);

        List<Item> items = spotify.getItems();
        List<String> ids = items.stream().map(Item::getId).collect(Collectors.toList());


        //FUCK TIME TODO
//          ids.forEach(id -> {
//            try {
//                String audioAnalysisParameters = spotifySongInfoService.getAudioAnalysisParameters(accessToken, id);
//                try(PrintWriter topTracksFile = new PrintWriter(now  + id + " - audio analysis.txt")) {
//                    topTracksFile.println(audioAnalysisParameters);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });


        String stringIds = String.join(",", ids);
        String audioFeatures = spotifySongInfoService.getAudioFeatures(accessToken, stringIds);

        try (PrintWriter topTracksFile = new PrintWriter(now + " - audiofeatures.txt")) {
            topTracksFile.println(audioFeatures);
        }

        String genreSeeds = spotifySongInfoService.getGenreSeeds(accessToken);

        try (PrintWriter topTracksFile = new PrintWriter(now + " - genreSeeds.txt")) {
            topTracksFile.println(genreSeeds);
        }

        String playlists = spotifySongInfoService.getPlaylists(accessToken);


        try (PrintWriter topTracksFile = new PrintWriter(now + " - playlists.txt")) {
            topTracksFile.println(playlists);
        }
        return spotify;
    }
}
