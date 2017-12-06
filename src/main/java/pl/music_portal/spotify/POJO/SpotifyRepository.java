package pl.music_portal.spotify.POJO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotifyRepository extends JpaRepository<Spotify, Long> {
    //    Spotify getByUserUserId(long userId);
    Spotify getByUserUserId(long userId);
//    List<Item> getFirstBySpotifyId(long spotifyId);

}
