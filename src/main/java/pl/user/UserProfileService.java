package pl.user;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserProfileService(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public void addDescription(String description) {
        User user = userService.authTest();
        user.setDescription(description);
        userRepository.save(user);
    }

    public void editEmail(String email) {
        User user = userService.authTest();
        user.setEmail(email);
        userRepository.save(user);
    }

    public void editPassword(String newPassword, String oldPassword) {
        User user = userService.authTest();
        if(passwordEncoder.encode(oldPassword).equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }
        else {
            throw new AccessDeniedException("Password not the same!");
        }
    }
}
