package pl.friends;

import org.springframework.web.bind.annotation.*;
import pl.model.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends/")
public class FriendsController {

    private final FriendsService friendsService;

    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    //TODO void is ok?
    @GetMapping("/friendrequest/send/{invitedUsername}")
    public void sendFriendRequest(@PathVariable String invitedUsername){
       friendsService.sendFriendRequest(invitedUsername);
    }

    @GetMapping("/friendsrequest/get/all")
    public List<FriendRequest> getFriendsRequestsList(){
        List<FriendRequest> friendsRequestsList = friendsService.getFriendsRequestsList();
        return friendsRequestsList;
    }

    @GetMapping("/firendrequest/accept/{requestId}")
    public void acceptRequest(@PathVariable long requestId){
        friendsService.acceptFriendRequest(requestId);
    }

    @GetMapping("/removerequest/{requestId}")
    public void removeRequest(@PathVariable long requestId){
        friendsService.removeFriendRequest(requestId);
    }

    @GetMapping("/get/all")
    public List<User> getFriends(){
        List<User> friends = friendsService.getFriends();
        return friends;
    }
}
