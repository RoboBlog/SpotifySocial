package pl;

import org.springframework.stereotype.Service;
import pl.model.User;
import pl.model.UserRepository;

/**
 * Created by maciek on 7/9/17.
 */
@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final SecurityContextService securityContextService;

    public ProfileService(UserRepository userRepository, SecurityContextService securityContextService) {
        this.userRepository = userRepository;
        this.securityContextService = securityContextService;
    }

    public User authTest() {
        String username = securityContextService.getUsername();
        User loggedUser = userRepository.findByUserName(username);
        return loggedUser;
    }
    public void setSpotifyAccessToken(String accessToken){
        User user = authTest();
        user.setSpotifyAccessToken(accessToken);
        userRepository.save(user);
    }
    public String getSpotifyAccessToken(){
        User user = authTest();
        return  user.getSpotifyAccessToken();
    }
}
