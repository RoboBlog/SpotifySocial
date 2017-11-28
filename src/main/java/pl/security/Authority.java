package pl.security;


import pl.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

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

    public void addUser(User user){
        this.users.add(user);
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", name=" + name +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority = (Authority) o;

        if (id != null ? !id.equals(authority.id) : authority.id != null) return false;
        if (name != authority.name) return false;
        return users != null ? users.equals(authority.users) : authority.users == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }


}