package pl.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.model.User;
import pl.model.UserRepository;

import javax.annotation.PostConstruct;

/**
 * Created by maciek on 7/8/17.
 */
@Component
public class RunAtStart {


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Autowired
    public RunAtStart(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void runAtStart(){
        User user = new User();
        user.setEmail("testmail@gmail.com");
        user.setPassword(passwordEncoder.encode("password"));
        user.setUserName("admin");
        user.setEnabled(1);
        //role add
        userRepository.save(user);
    }
}
