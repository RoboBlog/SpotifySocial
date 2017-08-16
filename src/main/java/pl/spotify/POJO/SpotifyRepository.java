package pl.spotify.POJO;

import org.springframework.data.repository.CrudRepository;

public interface SpotifyRepository extends CrudRepository<Spotify, Long> {
//    Spotify getByUserUserId(long userId);
    Spotify getByUserUserId(long userId);
//    List<Item> getFirstBySpotifyId(long spotifyId);

}
