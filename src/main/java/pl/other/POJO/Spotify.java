
package pl.other.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import pl.model.User;

import javax.persistence.*;

@Entity
public class Spotify {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotifyId;

    @OneToOne
    private User user;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("offset")
    @Expose
    private Integer offset;
//    @SerializedName("previous")
//    @Expose
//    private Object previous;
    @SerializedName("limit")
    @Expose
    private Integer limite;
    @SerializedName("href")
    @Expose
    private String href;

    @OneToMany(cascade=CascadeType.ALL)
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public Spotify(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spotify(Long spotifyId) {
        this.spotifyId = spotifyId;
    }

    public Long getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(Long spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

//    public Object getPrevious() {
//        return previous;
//    }
//
//    public void setPrevious(Object previous) {
//        this.previous = previous;
//    }

    public Integer getLimit() {
        return limite;
    }

    public void setLimit(Integer limit) {
        this.limite = limit;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

//    public List<Item> getItems() {
//        return items;
//    }

//    public void setItems(List<Item> items) {
//        this.items = items;
//    }


    public Spotify() {
    }

    public Spotify(String next, Integer total, Integer offset, Integer limite, String href) {
        this.next = next;
        this.total = total;
        this.offset = offset;
        this.limite = limite;
        this.href = href;
    }

    @Override
    public String toString() {
        return "Spotify{" +
                "id="+spotifyId+
                ", next='" + next + '\'' +
                ", total=" + total +
                ", offset=" + offset +
//                ", previous=" + previous +
                ", limit=" + limite +
                ", href='" + href + '\'' +
//                ", items=" + items +
                '}';
    }
}
