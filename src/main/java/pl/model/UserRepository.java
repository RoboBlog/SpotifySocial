package pl.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by maciek on 7/8/17.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String username);
//    void save(User user);
}
