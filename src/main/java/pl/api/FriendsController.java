package pl.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.other.SecurityContextService;
import pl.model.*;
import pl.userProfile.FriendsService;

import java.util.List;

@RestController
@RequestMapping("/api/friends/")
public class FriendsController {
    private final UserRepository userRepository;
    private final FriendRequestRepository friendRequestRepository;
    private final SecurityContextService securityContextService;
    private final FriendsService friendsService;

    @Autowired
    public FriendsController(UserRepository userRepository, FriendRequestRepository friendRequestRepository, SecurityContextService securityContextService, FriendsService friendsService) {
        this.userRepository = userRepository;
        this.friendRequestRepository = friendRequestRepository;
        this.securityContextService = securityContextService;
        this.friendsService = friendsService;
    }

    @GetMapping("/sendfriendrequest/{invitedUsername}")
    public String sendFriendRequest(@PathVariable String invitedUsername){
       friendsService.sendFriendRequest(invitedUsername);
        return "cg you send friend request to " + invitedUsername;
    }

    @GetMapping("/getfriendsrequests")
    public List<FriendRequest> getFriendsRequestsList(){
        List<FriendRequest> friendsRequestsList = friendsService.getFriendsRequestsList();
        return friendsRequestsList;
    }

    @GetMapping("/acceptrequest/{requestId}")
    public String acceptRequest(@PathVariable long requestId){
        String response = friendsService.acceptFriendRequest(requestId);
        return response;
    }
    @GetMapping("/removerequest/{requestId}")
    public String removeRequest(@PathVariable long requestId){
        String response = friendsService.removeFriendRequest(requestId);
        return "remove is complete";
    }

    @GetMapping("/getfriends")
    public List<FriendRequest> getFriends(){
        List<FriendRequest> friends = friendsService.getFriends();
        return friends;
    }
}
