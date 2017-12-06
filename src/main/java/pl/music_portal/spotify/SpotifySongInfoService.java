package pl.music_portal.spotify;

import org.springframework.stereotype.Service;
import pl.util.HttpClient;

import java.io.IOException;
import java.util.Map;

@Service
public class SpotifySongInfoService {
    private final HttpClient httpClient;

    public SpotifySongInfoService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getAudioAnalysisParameters(String userAccessToken, String songId) throws IOException {
        String url = "https://api.spotify.com/v1/audio-analysis/" + songId;
        Map<String, String> headers = httpClient.setAuthHeader(userAccessToken);

        return httpClient.get(url, headers, 5000, 5000);
    }


    public String getAudioFeatures(String userAccessToken, String songsId) throws IOException {
        String url = "https://api.spotify.com/v1/audio-features/?ids=" + songsId;
        Map<String, String> headers = httpClient.setAuthHeader(userAccessToken);

        return httpClient.get(url, headers, 5000, 5000);
    }

    public String getGenreSeeds(String userAccessToken) throws IOException {
        String url = "https://api.spotify.com/v1/recommendations/available-genre-seeds";
        Map<String, String> headers = httpClient.setAuthHeader(userAccessToken);

        return httpClient.get(url, headers, 5000, 5000);
    }


    //TODO VERY LONG TIME
    public String getPlaylists(String userAccessToken) throws IOException {
        String url = "https://api.spotify.com/v1/me/playlists?limit=50";
        Map<String, String> headers = httpClient.setAuthHeader(userAccessToken);

        return httpClient.get(url, headers, 5000, 5000);
    }


}
