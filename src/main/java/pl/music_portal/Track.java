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

    @OneToOne
    private TrackDetails details;

    private String imageUrl;
    private String spotifyId;
    private String deezerId;
//    private String lastFm;
    //tags
    //... urls.

}
