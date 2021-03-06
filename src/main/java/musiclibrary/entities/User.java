package musiclibrary.entities;

import com.google.common.collect.ImmutableList;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import static musiclibrary.dbworks.dbconstants.DBconstants.COLLECTION_USERS;

@org.mongodb.morphia.annotations.Entity(COLLECTION_USERS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Entity implements Serializable {
    @Id
    private int id;
    private String name;
    @Reference
    private List<TrackList> trackLists;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTrackLists(ImmutableList<TrackList> trackLists) {
        this.trackLists = trackLists;
    }

    public User(int id, String name, List<TrackList> trackLists) {
        this.id = id;
        this.name = name;
        this.trackLists = ImmutableList.copyOf(trackLists);
    }

    private User() {
        id = -1;
        name = null;
        trackLists = null;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public TrackList getTrackList(int index) {
        return trackLists.get(index);
    }
    public List<TrackList> getTrackLists() {
        return trackLists;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", trackLists=" + trackLists +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, trackLists);
    }
}
