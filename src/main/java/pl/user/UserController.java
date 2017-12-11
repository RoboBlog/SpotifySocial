package pl.user;

import com.fasterxml.jackson.annotation.JsonView;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainer;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import pl.other.Views;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

//exception
//@CrossOrigin(origins = "${origins}")
@RestController
@RequestMapping("${ver}/user/")
public class UserController {

    private final UserService userService;
    private final UserProfileService userProfileService;
    private final PasswordResetService passwordResetService;

    public UserController(UserService userService, UserProfileService userProfileService, PasswordResetService passwordResetService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.passwordResetService = passwordResetService;
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

    @PostMapping("/reset/password")
    public void resetPassword(@RequestParam String userEmail) throws UnknownHostException {
        passwordResetService.resetPassword(userEmail);
    }


    @PostMapping("/changePassword")
    public void validatePasswordResetTokenAndChangeNewPassword(@RequestParam Long id,
                                                               @RequestParam String token,
                                                               @RequestParam String password) throws UnknownHostException {
        passwordResetService.validatePasswordResetToken(token, password, id);
    }

    @PutMapping("/edit/password")
    public void editPassword(@RequestParam String oldPassword,
                             @RequestParam String newPassword) {
        userProfileService.editPassword(newPassword, oldPassword);
    }

    @PutMapping("/edit/email")
    public void editEmail(@RequestParam String email) {
        userProfileService.editEmail(email);
    }

    @PostMapping("/add/address")
    public Address addAddress(@RequestParam String city,
                              @RequestParam String country) throws IOException {
        return userService.addAddress(city, country);
    }

    @PutMapping("/add/description")
    public void addDescription(@RequestParam String description) {
        userProfileService.addDescription(description);
    }
}
