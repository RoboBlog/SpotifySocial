package pl.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.model.User;
import pl.model.UserRepository;
import pl.userProfile.AccountActivationService;

import javax.annotation.PostConstruct;

/**
 * Created by maciek on 7/14/17.
 */
@Component
public class RunAtStart {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AccountActivationService accountActivationService;

    @Autowired
    public RunAtStart(PasswordEncoder passwordEncoder, UserRepository userRepository, AccountActivationService accountActivationService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.accountActivationService = accountActivationService;
    }

    @PostConstruct
    public void runAtStart(){
        User user = new User();
        user.setEmail("robovlogg@gmail.com");
        user.setPassword(passwordEncoder.encode("test"));
        user.setUsername("maciek");
        //role add
        userRepository.save(user);
        accountActivationService.sendActivationMail(user);


        User user2 = new User("karol", passwordEncoder.encode("test"), "karol@gmail.com",1);
        userRepository.save(user2);

    }
}