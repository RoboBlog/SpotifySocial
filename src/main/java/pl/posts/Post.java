package pl.posts;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.comments.Comment;
import pl.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    private LocalDateTime date;
    private String content;
    //TODO
    //html
    //image or images
//    private Long likes;
    @OneToMany
    private Set<Comment> comments;

    public void addComment(Comment comment){
       this.comments.add(comment);
    }
}
