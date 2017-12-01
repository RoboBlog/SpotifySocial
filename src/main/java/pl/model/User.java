package pl.model;

import com.fasterxml.jackson.annotation.JsonView;
//import pl.posts.Post;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import pl.posts.Post;
import pl.other.Views;
import pl.security.Authority;
import pl.spotify.POJO.Spotify;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userid")
    @JsonView(Views.Internal.class)
    private Long userId;
    //background
    //profile image

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

    private String description;

    //TODO new entity Friend is required?
    @OneToMany
    private List<User> friends;

    @JsonView(Views.Public.class)
    @Column(name ="enabled")
    private boolean enabled;
    @OneToOne
    private Spotify spotify;

    @JsonView(Views.Internal.class)
    @OneToOne
    @JoinColumn(name="addressId")
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userid")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private List<Authority> authorities = new LinkedList<>();

    public void addAuthority(Authority authority){
        this.authorities.add(authority);
    }

    public void addFriend(User user){
        this.friends.add(user);
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public Spotify getSpotify() {
        return spotify;
    }

    public void setSpotify(Spotify spotify) {
        this.spotify = spotify;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getDeezerAccessToken() {
        return deezerAccessToken;
    }

    public void setDeezerAccessToken(String deezerAccessToken) {
        this.deezerAccessToken = deezerAccessToken;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getSpotifyAccessToken() {
        return spotifyAccessToken;
    }

    public void setSpotifyAccessToken(String spotifyAccessToken) {
        this.spotifyAccessToken = spotifyAccessToken;
    }


//    public Spotify getSpotify() {
//        return spotify;
//    }
//
//    public void setSpotify(Spotify spotify) {
//        this.spotify = spotify;
//    }

    public String getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(String confirmationId) {
        this.confirmationId = confirmationId;
    }

    public User(String username, String password, String email, boolean enabled, Date lastPasswordResetDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public User(String username, String password, String email, boolean enabled) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.activationCode = UUID.randomUUID().toString();
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.activationCode = UUID.randomUUID().toString();

    }

    public User(){
        this.activationCode = UUID.randomUUID().toString();
    }

    public User(User user) {
        this.userId = user.userId;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
        this.enabled=user.enabled;
        this.activationCode = UUID.randomUUID().toString();

    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", spotifyAccessToken='" + spotifyAccessToken + '\'' +
                ", deezerAccessToken='" + deezerAccessToken + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", spotify=" + spotify +
                ", address=" + address +
                ", activationCode='" + activationCode + '\'' +
                ", confirmationId='" + confirmationId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
