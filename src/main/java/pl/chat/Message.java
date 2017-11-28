package pl.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String from;
    private String content;
    private LocalDateTime createdDate;


    public Message(String name, String from, String content) {
        this.name = name;
        this.from = from;
        this.content = content;
        this.createdDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", from='" + from + '\'' +
                ", content='" + content + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != null ? !id.equals(message.id) : message.id != null) return false;
        if (name != null ? !name.equals(message.name) : message.name != null) return false;
        if (from != null ? !from.equals(message.from) : message.from != null) return false;
        if (content != null ? !content.equals(message.content) : message.content != null) return false;
        return createdDate != null ? createdDate.equals(message.createdDate) : message.createdDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
