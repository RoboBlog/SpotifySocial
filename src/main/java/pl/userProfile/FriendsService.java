package pl.userProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.other.SecurityContextService;
import pl.model.FriendRequest;
import pl.model.FriendRequestRepository;

import java.util.List;
import java.util.Objects;

@Service
public class FriendsService {
    private final SecurityContextService securityContextService;
    private final FriendRequestRepository friendRequestRepository;

    @Autowired
    public FriendsService(SecurityContextService securityContextService, FriendRequestRepository friendRequestRepository) {
        this.securityContextService = securityContextService;
        this.friendRequestRepository = friendRequestRepository;
    }

    public void sendFriendRequest(String invitedUsername){
        String username = securityContextService.getUsername();
        FriendRequest request = new FriendRequest(username, invitedUsername);
        friendRequestRepository.save(request);
    }

    public List<FriendRequest> getFriendsRequestsList(){
        String username = securityContextService.getUsername();
        List<FriendRequest> friendsRequest = friendRequestRepository.getAllByRequestToAndIsAcceptIsFalse(username);
        return friendsRequest;
    }

    //@PreAuthorize
    public String acceptFriendRequest(long requestId){
        FriendRequest friendRequest = friendRequestRepository.getById(requestId);
        if(Objects.equals(friendRequest.getRequestFrom(), securityContextService.getUsername())){
            friendRequest.setAccept(true);
            friendRequestRepository.save(friendRequest);
            return "ok";
        }
        else{
            return "error";
        }
    }

    public String removeFriendRequest(long requestId){
        FriendRequest friendRequest = friendRequestRepository.getById(requestId);
        if(Objects.equals(friendRequest.getRequestFrom(), securityContextService.getUsername())){
            friendRequestRepository.delete(friendRequest);
            return "ok";
        }
        else{
            return "error";
        }
    }

    public List<FriendRequest> getFriends(){
        String username = securityContextService.getUsername();
        List<FriendRequest> friends = friendRequestRepository.getAllByRequestToAndIsAcceptIsTrue(username);
        return friends;
    }

}
