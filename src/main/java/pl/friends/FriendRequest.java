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

    @Override
    public String toString() {
        return "FriendRequest{" +
                "id=" + id +
                ", requestFrom='" + requestFrom + '\'' +
                ", requestTo='" + requestTo + '\'' +
                ", sendDate=" + sendDate +
                ", isAccept=" + isAccept +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FriendRequest that = (FriendRequest) o;

        if (isAccept != that.isAccept) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (requestFrom != null ? !requestFrom.equals(that.requestFrom) : that.requestFrom != null) return false;
        if (requestTo != null ? !requestTo.equals(that.requestTo) : that.requestTo != null) return false;
        return sendDate != null ? sendDate.equals(that.sendDate) : that.sendDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (requestFrom != null ? requestFrom.hashCode() : 0);
        result = 31 * result + (requestTo != null ? requestTo.hashCode() : 0);
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);
        result = 31 * result + (isAccept ? 1 : 0);
        return result;
    }
}
