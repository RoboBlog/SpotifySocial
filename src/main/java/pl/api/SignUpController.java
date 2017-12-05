package pl.api;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.model.User;
import pl.model.UserRepository;
import pl.userProfile.AccountActivationService;

import javax.validation.Valid;

@RequestMapping("${ver}")
@RestController
public class SignUpController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AccountActivationService accountActivationService;

    public SignUpController(PasswordEncoder passwordEncoder, UserRepository userRepository, AccountActivationService accountActivationService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.accountActivationService = accountActivationService;
    }

    @PostMapping("/signup") // TODO exception or failure and test activation
    public void signUp(@RequestBody @Valid User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        accountActivationService.sendActivationMail(user);
    }

}
