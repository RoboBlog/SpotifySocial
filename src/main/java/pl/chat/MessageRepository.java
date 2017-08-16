package pl.chat;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by maciek on 8/12/17.
 */
@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}
