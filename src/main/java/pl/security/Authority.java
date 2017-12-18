package pl.security;


import lombok.Data;
import pl.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Authority {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthorityName name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> users;

    public Authority(AuthorityName name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public Authority() {

    }

    public void addUser(User user) {
        this.users.add(user);
    }

}