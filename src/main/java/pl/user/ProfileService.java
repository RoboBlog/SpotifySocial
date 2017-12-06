package pl.userProfile;

import org.springframework.stereotype.Service;
import pl.user.User;
import pl.user.UserRepository;

@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final UserService userService;

    public ProfileService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    public void setSpotifyAccessToken(String accessToken, String username) {
        User user = userRepository.findByUsername(username);
        user.setSpotifyAccessToken(accessToken);
        userRepository.save(user);
    }

    public void setDeezerAccessToken(String accessToken, String username) {
        User user = userRepository.findByUsername(username);
        user.setDeezerAccessToken(accessToken);
        userRepository.save(user);
    }

    public String getSpotifyAccessToken() {
        User user = userService.authTest();
        return user.getSpotifyAccessToken();
    }

}
