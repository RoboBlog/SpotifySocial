package pl.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by maciek on 7/17/17.
 */
@RestController
@RequestMapping("/api/")
public class SecurityController {
    @PostMapping("/login")
    public void login(@RequestParam String username, @RequestParam String password){

    }
}
