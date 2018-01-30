package pl.music_portal;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

//@Builder
@Entity
@Data
public class Track {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private Album album;

    @ManyToMany
    private List<Artist> artist = new LinkedList<>();

    private int durationMs;
    //private boolean explicit
    //private int popularity //spotify

//    private Genre genre;
    private String imageUrl;
    //tags
    //spotify/deezer/.... urls.

}
