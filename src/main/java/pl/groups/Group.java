package pl.groups;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
