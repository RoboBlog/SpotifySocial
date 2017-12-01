package pl.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.model.User;
import pl.model.UserRepository;
import pl.userProfile.AccountActivationService;

import javax.validation.Valid;

@RequestMapping("/api/")
@RestController
public class SignUpController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AccountActivationService accountActivationService;


    @Autowired
    public SignUpController(PasswordEncoder passwordEncoder, UserRepository userRepository, AccountActivationService accountActivationService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.accountActivationService = accountActivationService;
    }

    @PostMapping("/signup") // TODO exception or failure and test activation
    public void signUp(@RequestBody @Valid User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        accountActivationService.sendActivationMail(user);
    }

}
