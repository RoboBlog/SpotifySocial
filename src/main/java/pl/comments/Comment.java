package pl.comments;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pl.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@SQLDelete(sql="Update comment SET deleted = true where id=?")
@Where(clause="deleted != true")
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne()
    private User author;
    private String content;
    private LocalDateTime date;
    private Long likes;
    private boolean deleted;
    //Todo add date in constructor


    public Comment(User author, String content) {
        this.author = author;
        this.content = content;
        this.date = LocalDateTime.now();
        this.likes = 0L;
        this.deleted = false;
    }
}
