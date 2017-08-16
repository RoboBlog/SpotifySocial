package pl.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by maciek on 8/13/17.
 */
@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest, Long> {
    List<FriendRequest> getAllByRequestToAndIsAcceptIsFalse(String requestTo);
    List<FriendRequest> getAllByRequestToAndIsAcceptIsTrue(String requestTo);
    FriendRequest getById(long id);
}
