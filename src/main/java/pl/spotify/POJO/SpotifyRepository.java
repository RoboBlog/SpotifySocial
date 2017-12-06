package pl.spotify.POJO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SpotifyRepository extends JpaRepository<Spotify, Long> {
    //    Spotify getByUserUserId(long userId);
    Spotify getByUserUserId(long userId);
//    List<Item> getFirstBySpotifyId(long spotifyId);

}
