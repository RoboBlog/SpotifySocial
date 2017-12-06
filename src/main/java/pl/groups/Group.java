package pl.groups;
import lombok.Data;
import pl.model.User;
import pl.posts.Post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
//    private tags
    //backgrond, icon
    //todo
//    private Set<User> admins;
//    private Set<User> moderators = new HashSet<>();
//    private Set<User> members = new HashSet<>();
////    private privacy level
//    private Set<Post> posts;
//    ///users amount
}
