package pl.userProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.model.*;

@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public ProfileService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    public void setSpotifyAccessToken(String accessToken, String username){
        User user = userRepository.findByUsername(username);
        user.setSpotifyAccessToken(accessToken);
        userRepository.save(user);
    }

    public void setDeezerAccessToken(String accessToken, String username){
        User user = userRepository.findByUsername(username);
        user.setDeezerAccessToken(accessToken);
        userRepository.save(user);
    }

    public String getSpotifyAccessToken(){
        User user = userService.authTest();
        return  user.getSpotifyAccessToken();
    }

}
