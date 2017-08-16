package pl.api;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.model.Address;
import pl.model.User;
import pl.other.Views;
import pl.userProfile.UserService;

import java.io.IOException;

// exception
@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @JsonView(Views.Public.class)
    @GetMapping("/profile")
    public pl.model.User profile(){
        pl.model.User user = userService.authTest();
        return user;
    }



    @PutMapping("/editAddress")
    public void editAddress(@RequestBody Address address) {
    }

    @PutMapping("/editPassword")
    public void editPassword(){

    }

    @PutMapping("/editEmail")
    public void editEmail(@RequestBody User user){ //@RequestParam?
        System.out.print(user);
        String email = user.getEmail();

    }

    @PostMapping("/addaddress")
    public void addAddress(@RequestParam String city, @RequestParam String country) throws IOException {
        userService.addAddress(city, country);
    }
}
