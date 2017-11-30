package pl.spotify;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.userProfile.ProfileService;
import pl.model.User;
import pl.spotify.POJO.Item;
import pl.spotify.POJO.Spotify;
import pl.spotify.POJO.SpotifyRepository;
import pl.userProfile.UserService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpotifyDataService {
    private final SpotifyRepository spotifyRepository;
    private final ProfileService profileService;
    private final UserService userService;

    @Autowired
    public SpotifyDataService(SpotifyRepository spotifyRepository, ProfileService profileService, UserService userService) {
        this.spotifyRepository = spotifyRepository;
        this.profileService = profileService;
        this.userService = userService;
    }

    public List<Item> getItems(){
        User user = userService.authTest();
        Spotify spotifyData = spotifyRepository.getByUserUserId(user.getUserId());
        List<Item> items = spotifyData.getItems();
        return items;
    }

    public List<String> getArtists(List<Item> items){
        List<String> artistsId = new LinkedList<>();

        items.forEach(item -> {
            artistsId.add(item.getArtists().get(0).getId());
        });

        return artistsId;
    }

    public String getArtistsJson(List<String> artistsId) throws IOException {
        String userAccessToken = profileService.getSpotifyAccessToken();
        String artistsIdList = artistsId.stream().collect(Collectors.joining(","));
        String url = "https://api.spotify.com/v1/artists?ids=" + artistsIdList;

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + userAccessToken)
                .build();

        Response response = client.newCall(request).execute();
        String body = response.body().string();

        return body;
    }

    public List<String> getFavoriteMusicGenres() throws IOException {
        List<Item> items = getItems();
        List<String> artistsList = getArtists(items);
        String artistsJson = getArtistsJson(artistsList);

        JSONObject js = new JSONObject(artistsJson);
        JSONArray artists = js.getJSONArray("artists");
        List<String> genresList = new LinkedList<>();

        for(int i=0; i<artists.length(); i++){
            JSONArray genres = artists.getJSONObject(i).getJSONArray("genres");
            genres.forEach(genre -> genresList.add(genre.toString()));
        }

        return genresList;
    } //enum?

}
