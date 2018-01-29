package pl.social.facebook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;
import pl.user.User;
import pl.user.UserRepository;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Slf4j
@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public FacebookConnectionSignup(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String execute(Connection<?> connection) {
        log.info("New user SignUp by Facebook");
        final User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setPassword(passwordEncoder.encode(randomAlphabetic(8)));
        user.setAccountEnabled(true);
        userRepository.save(user);
        return user.getUsername();
    }

}