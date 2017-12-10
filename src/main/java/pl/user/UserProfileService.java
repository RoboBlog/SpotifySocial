package pl.user;

import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserProfileService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void addDescription(String description) {
        User user = userService.authTest();
        user.setDescription(description);
        userRepository.save(user);
    }
}
