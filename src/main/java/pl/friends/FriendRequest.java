package pl.friends;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Data
@Entity
public class FriendRequest {
    @Id
    @GeneratedValue
    private Long id;
    private String requestFrom;
    private String requestTo;
    private LocalDateTime sendDate;
    private boolean isAccept;

    public FriendRequest(String requestTo, String requestFrom) {
        this.requestFrom = requestFrom;
        this.requestTo = requestTo;
    }
}
