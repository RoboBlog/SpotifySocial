package pl.spotify.POJO;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
//    List<Item> getAllBySpotifySpotifyId(long spotifyId);
//        Item getFirstBySpotifySpotifyId(long spotifyId);
}
