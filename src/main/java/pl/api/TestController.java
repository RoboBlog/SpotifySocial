package pl.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.model.User;
import pl.model.UserRepository;
import pl.other.Views;
import pl.spotify.TrackDto;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by maciek on 8/14/17.
 */
@CrossOrigin(origins = "http://localhost:8000")
@RequestMapping("/api/")
@RestController
public class TestController {
    private final UserRepository userRepository;

    @Autowired
    public TestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/gettracks") //TODO put spring JAK To ogarniac request param?
    public List<TrackDto> getTracks(){
        TrackDto track1 = new TrackDto("AC/DC", "TNT", "1");
        TrackDto track2 = new TrackDto("Pink Floyd", "Another Brick in The Wall", "2");
        TrackDto track3 = new TrackDto("Pink Floyd", "Take On Me", "3");
        TrackDto track4 = new TrackDto("Pink Floyd", "Where Is My Mind?", "4");
        TrackDto track5 = new TrackDto("Pink Floyd", "Shipbuilding", "5");
        TrackDto track6 = new TrackDto("Pink Floyd", "Beat It", "6");
        TrackDto track7 = new TrackDto("Pink Floyd", "Kill me", "7");

        List<TrackDto> list = new LinkedList<>();
        list.add(track1);
        list.add(track2);
        list.add(track3);
        list.add(track4);
        list.add(track5);
        list.add(track6);
        list.add(track7);

        return list;
    }

    @JsonView(Views.Public.class)
    @RequestMapping("/usr")
    public User usr() throws JsonProcessingException {
        User maciek = userRepository.findByUsername("maciek");

//        ObjectMapper mapper = new ObjectMapper();
//        String result = mapper
//                .writerWithView(Views.Public.class)
//                .writeValueAsString(maciek);

        return maciek;

        }

//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @PutMapping("/usr/{id}")
//    public String edit(@PathVariable int id, @RequestParam String name){
//
//        z\
//        return "test";
//    }
}
