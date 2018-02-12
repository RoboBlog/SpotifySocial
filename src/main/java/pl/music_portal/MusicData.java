package pl.music_portal;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class MusicData {

    @Id
    @GeneratedValue
    private Long id;
//    private providers
    @ManyToMany
    private List<Track> topTracks = new LinkedList<>();
    @ManyToMany
    private List<Genre> topGenres = new LinkedList<>();
    @ManyToMany
    private List<Artist> topArtists = new LinkedList<>();
    @ManyToMany
    private List<Album> topAlbums = new LinkedList<>();
    @ManyToMany
    private List<Track> recentlyPlayed = new LinkedList<>();

}
