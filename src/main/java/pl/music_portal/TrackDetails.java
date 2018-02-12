package pl.music_portal;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class TrackDetails {
    @Id
    @GeneratedValue
    private Long id;

    private int durationMs;
    private boolean explicit;
    private int popularity; //spotify

    @ManyToMany
    private List<Genre> genres;

}
