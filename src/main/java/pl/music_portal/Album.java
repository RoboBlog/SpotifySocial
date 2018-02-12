package pl.music_portal;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class Album {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    //copyrights
    private String imageUrl;
    private Date releaseDate;
    @OneToMany
    private List<Track> track = new LinkedList<>();
    //fans/followers/popularity
    @ManyToMany
    private List<Genre> genres = new LinkedList<>();
    //link
    //ratings
    //duration
}

