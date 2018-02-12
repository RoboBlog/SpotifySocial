package pl.music_portal;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.config.MapperConfiguration;
import pl.music_portal.spotify.POJO.Item;
import pl.music_portal.spotify.POJO.Spotify;

import java.util.LinkedList;
import java.util.List;

@Service
public class SpotifyMapper {

    private final MapperConfiguration mapperConfiguration;
    private final TrackRepository trackRepository;

    public SpotifyMapper(MapperConfiguration mapperConfiguration, TrackRepository trackRepository) {
        this.mapperConfiguration = mapperConfiguration;
        this.trackRepository = trackRepository;
    }

    public void mapTopTracks(){
        ModelMapper modelMapper = mapperConfiguration.modelMapper();

        Spotify spotifyTopTracks = new Spotify();
        List<Item> items = spotifyTopTracks.getItems();
        List<Track> topTracks = new LinkedList<>();

        items.forEach(item ->{
            if(trackRepository.existsBySpotifyId(item.getId())){
                Track track = modelMapper.map(item, Track.class);//relations
                trackRepository.save(track);
                topTracks.add(track);
            }

            else {
                topTracks.add(trackRepository.findBySpotifyId(item.getId()));
            }
        });
    }
}
