package musiclibrary.entities;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.io.Serializable;
import java.util.Objects;

import static musiclibrary.dbworks.dbconstants.DBconstants.COLLECTION_ARTISTS;

@org.mongodb.morphia.annotations.Entity(COLLECTION_ARTISTS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Artist extends Entity
        implements Serializable {
    @Id
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Artist(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private Artist() {
        id = -1;
        name = null;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
//        return "Artist [id=" + id + ", name=" + name + "]";
        return "{ " +
                "\"id\" : \"" + id +
                "\", \"name\" : \"" + name + "\" }";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        return this.id == ((Artist)obj).id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
