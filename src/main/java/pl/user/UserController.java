package pl.user;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

//exception
@CrossOrigin(origins = "${origins}")
@RestController
@RequestMapping("${ver}/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    @JsonView(Views.Public.class)
    @GetMapping("/profile")
    public User profile() {
        User user = userService.authTest();
        return user;
    }


    @PutMapping("/editAddress")
    public void editAddress(@RequestBody Address address) {
    }

    @PutMapping("/editPassword")
    public void editPassword() {

    }

    @PutMapping("/editEmail")
    public void editEmail(@RequestBody User user) { //@RequestParam?
        System.out.print(user);
        String email = user.getEmail();

    }

    @PostMapping("/addaddress")
    public void addAddress(@RequestParam String city, @RequestParam String country) throws IOException {
        userService.addAddress(city, country);
    }
}
