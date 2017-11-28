package pl.comments;

import pl.model.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User author;
    private String content;
    private LocalDateTime date;
    private Long likes;

    //Todo add date in constructor


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", likes=" + likes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != null ? !id.equals(comment.id) : comment.id != null) return false;
        if (author != null ? !author.equals(comment.author) : comment.author != null) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        if (date != null ? !date.equals(comment.date) : comment.date != null) return false;
        return likes != null ? likes.equals(comment.likes) : comment.likes == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (likes != null ? likes.hashCode() : 0);
        return result;
    }
}
