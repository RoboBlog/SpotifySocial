package pl.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest, Long> {
    List<FriendRequest> getAllByRequestToAndIsAcceptIsFalse(String requestTo);
    List<FriendRequest> getAllByRequestToAndIsAcceptIsTrue(String requestTo);
    FriendRequest getById(long id);
}
