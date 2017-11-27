package pl.posts;

import pl.comments.Comment;
import pl.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addComment(Comment comment){
       this.comments.add(comment);
    }
    public Post() {
    }
}
