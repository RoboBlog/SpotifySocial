package pl.comments;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pl.user.User;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
@SQLDelete(sql="Update comment SET deleted = true where id=?")
@Where(clause="deleted != true")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User author;
    private String content;
    private ZonedDateTime date = ZonedDateTime.now();
    private Long likes;
    private boolean deleted;
    //Todo add date in constructor


    public Comment(User author, String content) {
        this.author = author;
        this.content = content;
        this.likes = 0L;
        this.deleted = false;
    }
}
