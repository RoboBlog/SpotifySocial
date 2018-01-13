package pl.other;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.music_portal.spotify.POJO.Spotify;
import pl.music_portal.spotify.SpotifyApiService;
import pl.user.User;
import pl.user.UserRepository;
import pl.music_portal.spotify.TrackDto;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@RequestMapping("${ver}")
@RestController
public class TestController {
    private final UserRepository userRepository;
    private final SpotifyApiService spotifyApiService;

    public TestController(UserRepository userRepository, SpotifyApiService spotifyApiService) {
        this.userRepository = userRepository;
        this.spotifyApiService = spotifyApiService;
    }

    @GetMapping("/gettracks") //TODO put spring JAK To ogarniac request param?
    public List<TrackDto> getTracks() {
        TrackDto track1 = new TrackDto("AC/DC", "TNT", "1");
        TrackDto track2 = new TrackDto("Pink Floyd", "Another Brick in The Wall", "2");
        TrackDto track3 = new TrackDto("Pink Floyd", "Take On Me", "3");
        TrackDto track4 = new TrackDto("Pink Floyd", "Where Is My Mind?", "4");
        TrackDto track5 = new TrackDto("Pink Floyd", "Shipbuilding", "5");
        TrackDto track6 = new TrackDto("Pink Floyd", "Beat It", "6");
        TrackDto track7 = new TrackDto("Pink Floyd", "Kill me", "7");
        TrackDto track8 = new TrackDto("Pink Floyd2", "Kill me2", "72");
        TrackDto track9 = new TrackDto("Pink FloydX", "Kill meX", "72");

        List<TrackDto> list = new LinkedList<>();
        list.add(track1);
        list.add(track2);
        list.add(track3);
        list.add(track4);
        list.add(track5);
        list.add(track6);
        list.add(track7);
        list.add(track8);
        list.add(track9);

        return list;
    }

    @JsonView(Views.Public.class)
    @GetMapping("/usr")
    public User usr() throws JsonProcessingException {
        User maciek = userRepository.findByUsername("maciek");

//        ObjectMapper mapper = new ObjectMapper();
//        String result = mapper
//                .writerWithView(Views.Public.class)
//                .writeValueAsString(maciek);

        return maciek;

    }

    @GetMapping("testtracks")
    public Spotify testTracks() throws IOException {
        Spotify spotify = spotifyApiService.saveTopTracks();
        return spotify;
    }

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @PutMapping("/usr/{id}")
//    public String edit(@PathVariable int id, @RequestParam String name){
//
//        z\
//        return "test";
//    }
}
