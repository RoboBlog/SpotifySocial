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
}
