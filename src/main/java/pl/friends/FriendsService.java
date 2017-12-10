package pl.friends;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import pl.user.User;
import pl.user.UserService;

import java.util.List;
import java.util.Objects;

@Service
public class FriendsService {

    private final UserService userService;
    private final FriendRequestRepository friendRequestRepository;

    public FriendsService(UserService userService, FriendRequestRepository friendRequestRepository) {
        this.userService = userService;
        this.friendRequestRepository = friendRequestRepository;
    }

    //TODO relation is necessary?
    public void sendFriendRequest(String invitedUsername) {
        String username = userService.getUsername();
        FriendRequest request = new FriendRequest(username, invitedUsername);
        friendRequestRepository.save(request);
    }

    public List<FriendRequest> getFriendsRequestsList() {
        String username = userService.getUsername();
        List<FriendRequest> friendsRequest = friendRequestRepository.getAllByRequestToAndIsAcceptIsFalse(username);
        return friendsRequest;
    }


    public void acceptFriendRequest(long requestId) {
        FriendRequest friendRequest = friendRequestRepository.getById(requestId);
        if (Objects.equals(friendRequest.getRequestFrom(), userService.getUsername())) {
            friendRequest.setAccept(true);
            friendRequestRepository.save(friendRequest);
        } else {
            throw new AccessDeniedException("Access denied!");
        }
    }

    public void removeFriendRequest(long requestId) {
        FriendRequest friendRequest = friendRequestRepository.getById(requestId);
        if (Objects.equals(friendRequest.getRequestFrom(), userService.getUsername())) {
            friendRequestRepository.delete(friendRequest);
        } else {
            throw new AccessDeniedException("Access denied!");
        }
    }

    public List<User> getFriends() {
        User user = userService.authTest();
        List<User> friends = user.getFriends();
        return friends;
    }

}
