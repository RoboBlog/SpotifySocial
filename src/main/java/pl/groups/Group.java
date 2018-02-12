package pl.groups;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import pl.posts.Post;
import pl.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@SQLDelete(sql="Update group SET deleted = true where id=?")
@Where(clause="deleted != true")
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private boolean deleted;
    //private tags;
    private String backgroundUrl;
    private String iconUrl;

    @ManyToMany
    private Set<User> admins = new HashSet<>();
    @ManyToMany
    private Set<User> moderators = new HashSet<>();
    @ManyToMany
    private Set<User> members = new HashSet<>();
//  private privacy level
    @OneToMany
    private List<Post> posts = new LinkedList<>();
}
