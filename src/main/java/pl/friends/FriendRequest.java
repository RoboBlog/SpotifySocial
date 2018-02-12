package pl.friends;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;


@Data
@Entity
public class FriendRequest {
    @Id
    @GeneratedValue
    private Long id;
    private String requestFrom;
    private String requestTo;
    private ZonedDateTime sendDate = ZonedDateTime.now();
    private boolean isAccept;

    public FriendRequest(String requestTo, String requestFrom) {
        this.requestFrom = requestFrom;
        this.requestTo = requestTo;
    }
}
