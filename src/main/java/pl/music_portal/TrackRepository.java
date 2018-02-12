package pl.music_portal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    boolean existsBySpotifyId(String spotifyId);
    Track findBySpotifyId(String spotifyId);
}
