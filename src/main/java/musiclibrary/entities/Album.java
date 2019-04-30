package musiclibrary.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.io.Serializable;
import java.util.List;

import static musiclibrary.dbworks.dbconstants.DBconstants.COLLECTION_ALBUMS;

@JsonIgnoreProperties(ignoreUnknown = true)
@org.mongodb.morphia.annotations.Entity(COLLECTION_ALBUMS)
public class Album extends TrackCollection implements Serializable {
    @Id
    private int id;
    private String name;
    @Reference
    private Artist artist;
    @Reference
    private List<Track> tracks;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album(int id, String name, Artist artist, List<Track> tracks) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.tracks = tracks;
    }

    public Album() {
        id = -1;
        name = null;
        artist = null;
        tracks = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
