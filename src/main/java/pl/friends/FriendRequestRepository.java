package pl.friends;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    List<FriendRequest> findAllByRequestToAndIsAcceptIsFalse(String requestTo);

    List<FriendRequest> findAllByRequestToAndIsAcceptIsTrue(String requestTo);

    FriendRequest findById(long id);
}
