//
//package pl.music_portal.spotify.POJO;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//import lombok.Data;
//import pl.user.User;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Data
////@Entity
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class Spotify {
//
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Long spotifyId;
////
////    @OneToOne
////    public User user;
////    @OneToMany(cascade = CascadeType.ALL)
//    @JsonProperty("items")
//    public List<Item> items = null;
//    @JsonProperty("total")
//    public Long total;
//    @JsonProperty("limit")
//    public Long limite;
//    @JsonProperty("offset")
//    public Long offset;
//    @JsonProperty("href")
//    public String href;
//    @JsonProperty("previous")
//    public Object previous;
//    @JsonProperty("next")
//    public String next;
//
//
//    public Spotify(String next, Long total, Long offset, Long limite, String href) {
//        this.next = next;
//        this.total = total;
//        this.offset = offset;
//        this.limite = limite;
//        this.href = href;
//    }
//
//    public Spotify(List<Item> items, Long total, Long limite, Long offset, String href, Object previous, String next) {
//        this.items = items;
//        this.total = total;
//        this.limite = limite;
//        this.offset = offset;
//        this.href = href;
//        this.previous = previous;
//        this.next = next;
//    }
//
//    public Spotify(List<Item> items, Long total, Long limite, Long offset, String href, String next) {
//        this.items = items;
//        this.total = total;
//        this.limite = limite;
//        this.offset = offset;
//        this.href = href;
//        this.next = next;
//    }
//
//    public Spotify() {
//    }
//}
