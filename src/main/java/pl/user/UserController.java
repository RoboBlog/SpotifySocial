package pl.user;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import pl.other.Views;

import java.io.IOException;

//exception
@CrossOrigin(origins = "${origins}")
@RestController
@RequestMapping("${ver}/user/")
public class UserController {

    private final UserService userService;
    private final UserProfileService userProfileService;

    public UserController(UserService userService, UserProfileService userProfileService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
    }


    @JsonView(Views.Public.class)
    @GetMapping("/profile")
    public User profile() {
        //TODO change name to getUser
        User user = userService.authTest();
        return user;
    }


    @PutMapping("/edit/address")
    public void editAddress(@RequestBody Address address) {
    }

//    @PostMapping("/reset/password")
//    public void resetPassword(@RequestParam String userEmail) {
//        userService.resetPassword(userEmail);
//    }

    @PutMapping("/edit/password")
    public void editPassword() {

    }

    @PutMapping("/edit/email")
    public void editEmail(@RequestBody User user) { //@RequestParam?
        System.out.print(user);
        String email = user.getEmail();

    }

    @PostMapping("/add/address")
    public Address addAddress(@RequestParam String city, @RequestParam String country) throws IOException {
        return userService.addAddress(city, country);
    }

    @PutMapping("/add/description")
    public void addDescription(@RequestParam String description) {
        userProfileService.addDescription(description);
    }
}
