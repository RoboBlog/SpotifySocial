package pl.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.other.Views;
import pl.posts.Post;
import pl.security.Authority;
import pl.music_portal.spotify.POJO.Spotify;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    @JsonView(Views.Internal.class)
    private Long userId;

    @JsonView(Views.Internal.class)
    private String spotifyAccessToken;

    @JsonView(Views.Internal.class)
    private String deezerAccessToken;

    @JsonView(Views.Public.class)
    @Column(name = "username", unique = true)
    @NotBlank
    private String username;

    @JsonView(Views.Internal.class)
    @Column(name = "password")
    @NotBlank
    private String password;

    @JsonView(Views.Public.class)
    @Column(name = "email")
    @Email
    private String email;

    @JsonView(Views.Public.class)
    private String description;

    //TODO new entity Friend is required?
    @JsonView(Views.Public.class)
    @OneToMany
    private List<User> friends;

    @JsonView(Views.Public.class)
    @Column(name = "enabled")
    private boolean accountEnabled;

//    @JsonView(Views.Public.class)
//    @OneToOne
//    private Spotify spotify;

    @JsonView(Views.Internal.class)
    @OneToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @JsonView(Views.Internal.class)
    private String activationCode;

    @JsonView(Views.Internal.class)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    @JsonView(Views.Internal.class)
    @OneToMany
    private Set<Post> posts = new HashSet<>();

    @JsonView(Views.Internal.class)
    private String confirmationId;

//    @JsonView(Views.Public.class)
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userid")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private List<Authority> authorities = new LinkedList<>();


    public void addFriend(User user) {
        this.friends.add(user);
    }

    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }


    public User(String username, String password, String email, boolean accountEnabled, Date lastPasswordResetDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountEnabled = accountEnabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public User(String username, String password, String email, boolean accountEnabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountEnabled = accountEnabled;
        this.activationCode = UUID.randomUUID().toString();
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.activationCode = UUID.randomUUID().toString();

    }

    public User() {
        this.activationCode = UUID.randomUUID().toString();
    }

    public User(User user) {
        this.userId = user.userId;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
        this.accountEnabled = user.accountEnabled;
        this.activationCode = UUID.randomUUID().toString();

    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", spotifyAccessToken='" + spotifyAccessToken + '\'' +
                ", deezerAccessToken='" + deezerAccessToken + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", friends=" + friends +
                ", accountEnabled=" + accountEnabled +
//                ", spotify=" + spotify +
                ", address=" + address +
                ", activationCode='" + activationCode + '\'' +
                ", lastPasswordResetDate=" + lastPasswordResetDate +
                ", posts=" + posts +
                ", confirmationId='" + confirmationId + '\'' +
                '}';
    }
}
