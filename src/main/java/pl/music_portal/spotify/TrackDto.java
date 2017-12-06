package pl.music_portal.spotify;

public class TrackDto {
    private String artist;
    private String name;
    private String trackId;

    public TrackDto(String artist, String name, String trackId) {
        this.artist = artist;
        this.name = name;
        this.trackId = trackId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }
}
