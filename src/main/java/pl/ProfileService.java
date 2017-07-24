package pl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.model.Friend;
import pl.model.FriendRepository;
import pl.model.User;
import pl.model.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final SecurityContextService securityContextService;
    private final FriendRepository friendRepository;

    @Autowired
    private ProfileService(UserRepository userRepository, SecurityContextService securityContextService, FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.securityContextService = securityContextService;
        this.friendRepository = friendRepository;
    }

    public User authTest() {
        String username = securityContextService.getUsername();
        User loggedUser = userRepository.findByUsername(username);
        return loggedUser;
    }
    public void setSpotifyAccessToken(String accessToken, String username){
        User user = userRepository.findByUsername(username);
        user.setSpotifyAccessToken(accessToken);
        userRepository.save(user);
    }
    public String getSpotifyAccessToken(){
        User user = authTest();
        return  user.getSpotifyAccessToken();
    }

}
