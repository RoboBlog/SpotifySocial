package pl.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ProfileService;
import pl.SecurityContextService;
import pl.other.GsonTest;
import pl.spotify.SpotifyGetService;
import pl.spotify.SpotifyLoginService;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by maciek on 7/23/17.
 */

@RestController
public class API {

    @RequestMapping
    public String testController(){
        return "test";
    }

}
