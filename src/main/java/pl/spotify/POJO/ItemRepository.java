package pl.spotify.POJO;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
//    List<Item> getAllBySpotifySpotifyId(long spotifyId);
//        Item getFirstBySpotifySpotifyId(long spotifyId);
}
