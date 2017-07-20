package pl.userProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ProfileService;
import pl.SecurityContextService;
import pl.model.Friend;
import pl.model.FriendRepository;
import pl.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by maciek on 7/14/17.
 */
@Service
public class FriendsService {
    private final ProfileService profileService;
    private final FriendRepository friendRepository;

    @Autowired
    public FriendsService(ProfileService profileService, FriendRepository friendRepository) {
        this.profileService = profileService;
        this.friendRepository = friendRepository;
    }

//    public Set<Friend> getAllFriends(){
//        User user = profileService.authTest();
//        get friends from repository
//        return friends;
//    }

    public void addFriend(User friend){
        User user = profileService.authTest();
        Friend newFriend = new Friend(friend);
        friendRepository.save(newFriend);
        List<Friend> friends = new ArrayList<>();
        friends.add(newFriend);
        //add one friend in repository
        user.setFriends(friends);
    }

    public void removeFriend(int friendId){

    }

}
