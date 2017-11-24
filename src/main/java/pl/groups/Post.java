package pl.groups;

import pl.model.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany
    private User user;
    private LocalDateTime date;
    private String content;
    //html
    //image or images
    private Long likes;
    @OneToMany
    private Set<Comment> comments;

}
