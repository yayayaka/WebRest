package musiclibrary.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import java.io.Serializable;
import java.util.Objects;

import static musiclibrary.dbworks.dbconstants.DBconstants.COLLECTION_TRACKS;

@org.mongodb.morphia.annotations.Entity(COLLECTION_TRACKS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track extends Entity implements Serializable {
    @Id
    private int id;
    private String name;
    @Reference
    private Artist artist;
    private double trackLength;
    @Embedded
    private Genre genre;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setTrackLength(double trackLength) {
        this.trackLength = trackLength;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    private Track() {
        id = -1;
        name = null;
        artist = null;
        trackLength = -1.0;
        genre = Genre.none;
    }

    public Track(int id, String name, Artist artist,
                 double trackLength, Genre genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        if (trackLength <= 0)
            throw new NumberFormatException("Недопустимое значение длины трека");
        this.trackLength = trackLength;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public Artist getArtist() {
        return artist;
    }

    public double getTrackLength() {
        return trackLength;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", artist=" + artist +
                ", trackLength=" + trackLength +
                ", genre=" + genre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return id == track.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, artist, trackLength, genre);
    }
}

