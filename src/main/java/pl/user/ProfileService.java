package pl.user;

import org.springframework.stereotype.Service;
import pl.security.SecurityContextService;

@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final SecurityContextService securityContextService;

    public ProfileService(UserRepository userRepository, UserService userService, SecurityContextService securityContextService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.securityContextService = securityContextService;
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
        User user = securityContextService.getLoggedUser();
        return user.getSpotifyAccessToken();
    }

}
