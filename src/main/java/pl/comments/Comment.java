package pl.comments;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pl.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@Entity
@SQLDelete(sql="Update comment SET deleted = true where id=?")
@Where(clause="deleted != true")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User author;
    private String content;
    private LocalDateTime date;
    private Long likes;
    private boolean deleted;
    //Todo add date in constructor
}
