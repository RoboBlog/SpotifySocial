
package pl.music_portal.spotify.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import pl.user.User;

import javax.persistence.*;
import java.util.List;

@Data
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

    @OneToMany(cascade = CascadeType.ALL)
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    public Spotify(String next, Integer total, Integer offset, Integer limite, String href) {
        this.next = next;
        this.total = total;
        this.offset = offset;
        this.limite = limite;
        this.href = href;
    }
}
