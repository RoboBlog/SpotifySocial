package pl.user;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.security.SecurityContextService;

@Service
public class UserProfileService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContextService securityContextService;

    public UserProfileService(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder, SecurityContextService securityContextService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.securityContextService = securityContextService;
    }

    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }

    public void addDescription(String description) {
        User user = securityContextService.getLoggedUser();
        user.setDescription(description);
        userRepository.save(user);
    }

    public void editEmail(String email) {
        User user = securityContextService.getLoggedUser();
        user.setEmail(email);
        userRepository.save(user);
    }

    public void editPassword(String newPassword, String oldPassword) {
        User user = securityContextService.getLoggedUser();
        if(passwordEncoder.encode(oldPassword).equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }
        else {
            throw new AccessDeniedException("Password not the same!");
        }
    }

//    public void deleteAccount()
}
