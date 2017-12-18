package pl.friends;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import pl.security.SecurityContextService;
import pl.user.User;
import pl.user.UserService;

import java.util.List;
import java.util.Objects;

@Service
public class FriendsService {

    private final FriendRequestRepository friendRequestRepository;
    private final SecurityContextService securityContextService;

    public FriendsService(FriendRequestRepository friendRequestRepository, SecurityContextService securityContextService) {
        this.friendRequestRepository = friendRequestRepository;
        this.securityContextService = securityContextService;
    }

    //TODO relation is necessary?
    public void sendFriendRequest(String invitedUsername) {
        String username = securityContextService.getLoggedUsername();
        FriendRequest request = new FriendRequest(username, invitedUsername);
        friendRequestRepository.save(request);
    }

    public List<FriendRequest> getFriendsRequestsList() {
        String username = securityContextService.getLoggedUsername();

        List<FriendRequest> friendsRequest = friendRequestRepository.findAllByRequestToAndIsAcceptIsFalse(username);
        return friendsRequest;
    }


    public void acceptFriendRequest(long requestId) {
        FriendRequest friendRequest = friendRequestRepository.findById(requestId);
        if (Objects.equals(friendRequest.getRequestFrom(), securityContextService.getLoggedUsername())) {
            friendRequest.setAccept(true);
            friendRequestRepository.save(friendRequest);
        } else {
            throw new AccessDeniedException("Access denied!");
        }
    }

    public void removeFriendRequest(long requestId) {
        FriendRequest friendRequest = friendRequestRepository.findById(requestId);
        if (Objects.equals(friendRequest.getRequestFrom(), securityContextService.getLoggedUsername())) {
            friendRequestRepository.delete(friendRequest);
        } else {
            throw new AccessDeniedException("Access denied!");
        }
    }

    public List<User> getFriends() {
        User user = securityContextService.getLoggedUser();
        List<User> friends = user.getFriends();
        return friends;
    }

}
