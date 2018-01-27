package pl.music_portal;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Entity
@Data
public class Track {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Album album;

    private Artist artist;

    private int durationMs;
    //private boolean explicit
    //private int popularity //spotify
    private Genre genre;
    private String imageUrl;
    //tags
    //spotify/deezer/.... urls.

}
