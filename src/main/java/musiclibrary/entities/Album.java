package musiclibrary.entities;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.mongodb.morphia.annotations.Id;
import java.io.Serializable;

import static musiclibrary.dbworks.dbconstants.DBconstants.COLLECTION_ALBUMS;

@JsonIgnoreProperties(ignoreUnknown = true)
@org.mongodb.morphia.annotations.Entity(COLLECTION_ALBUMS)
public class Album extends Entity implements Serializable {
    @Id
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Album(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Album() {
        id = -1;
        name = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
