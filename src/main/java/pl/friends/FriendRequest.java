package pl.friends;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class FriendRequest {
    @Id
    @GeneratedValue
    private Long id;
    private String requestFrom;
    private String requestTo;
    private LocalDateTime sendDate;
    private boolean isAccept;

    public FriendRequest() {
    }

    public FriendRequest(String requestFrom, String requestTo) {
        this.requestFrom = requestFrom;
        this.requestTo = requestTo;
        this.sendDate = LocalDateTime.now();
        this.isAccept = false;
    }

    public boolean isAccept() {
        return isAccept;
    }

    public void setAccept(boolean accept) {
        isAccept = accept;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }

    public LocalDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(LocalDateTime sendDate) {
        this.sendDate = sendDate;
    }
}
