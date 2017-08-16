package pl.model;

import com.fasterxml.jackson.annotation.JsonView;
import pl.other.Views;
import pl.spotify.POJO.Spotify;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userid")
    @JsonView(Views.Internal.class)
    private Long userId;

    @JsonView(Views.Internal.class)
    private String spotifyAccessToken;
    @JsonView(Views.Internal.class)
    private String deezerAccessToken;

    @JsonView(Views.Public.class)
    @Column(name = "username", unique = true)
    private String username;

    @JsonView(Views.Internal.class)
    @Column(name = "password")
    private String password;

    @JsonView(Views.Public.class)
    @Column(name = "email")
    private String email;
//    @OneToMany
//    @JoinColumn(name = "friendId")
//    private List<Friend> friends;

    @JsonView(Views.Public.class)
    @Column(name ="enabled")
    private int enabled;
    @OneToOne
    private Spotify spotify;

    @JsonView(Views.Internal.class)
    @OneToOne
    @JoinColumn(name="addressId")
    private Address address;
    @JsonView(Views.Internal.class)
    private String activationCode;

//    private FriendRequest friendRequest;



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
//    @OneToOne
//    private UserRole userRole;


//    public Spotify getSpotify() {
//        return spotify;
//    }
//
//    public void setSpotify(Spotify spotify) {
//        this.spotify = spotify;
//    }
    @JsonView(Views.Internal.class)
    private String confirmationId;

//    public UserRole getUserRole() {
//        return userRole;
//    }
//
//    public void setUserRole(UserRole userRole) {
//        this.userRole = userRole;
//    }

    public String getConfirmationId() {
        return confirmationId;
    }

    public void setConfirmationId(String confirmationId) {
        this.confirmationId = confirmationId;
    }

    public User(String username, String password, String email, int enabled) {
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

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public Long getUserid() {
        return userId;
    }

    public void setUserid(Long userid) {
        this.userId = userid;
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
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", spotify=" + spotify +
                ", address=" + address +
                ", activationCode='" + activationCode + '\'' +
                ", confirmationId='" + confirmationId + '\'' +
                '}';
    }
}
