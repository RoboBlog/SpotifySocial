package pl.posts;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pl.comments.Comment;
import pl.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@SQLDelete(sql="Update post SET deleted = true where id=?")
@Where(clause="deleted != true")
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    private LocalDateTime dateAdded = LocalDateTime.now();
    private String content;
    //TODO
    //html
    //image or images
//    private Long likes;
    @OneToMany
    private Set<Comment> comments;
    private boolean deleted;

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public Post() {
    }

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
    }
}
