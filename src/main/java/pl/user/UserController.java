package pl.user;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.*;
import pl.email.EmailService;
import pl.other.Views;
import pl.security.SecurityContextService;

import java.io.IOException;
import java.net.UnknownHostException;

//exception
//@CrossOrigin(origins = "${origins}")
@RestController
@RequestMapping("${ver}/user/")
public class UserController {

    private final UserService userService;
    private final UserProfileService userProfileService;
    private final PasswordResetService passwordResetService;
    private final SecurityContextService securityContextService;
    private final AccountDeletionService accountDeletionService;

    public UserController(UserService userService, UserProfileService userProfileService, PasswordResetService passwordResetService, SecurityContextService securityContextService, AccountDeletionService accountDeletionService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
        this.passwordResetService = passwordResetService;
        this.securityContextService = securityContextService;
        this.accountDeletionService = accountDeletionService;
    }


    @JsonView(Views.Public.class)
    @GetMapping("/profile")
    public User profile() {
        //TODO change name to getUser
        User user = securityContextService.getLoggedUser();
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

    @GetMapping("/delete/account/init")
    public void initDeleteUserAccountProcess() throws UnknownHostException {
        accountDeletionService.initDeleteUserAccountProcess();
    }

    @GetMapping("/delete/account/confirm")
    public void confirmDeleteUserAccountProcess(@RequestParam Long userId) {
        accountDeletionService.confirmDeleteUserAccountProcess(userId);
    }

}
