package musiclibrary.entities;

import java.util.List;

public abstract class TrackCollection extends Entity {
    private int id;
    private String name;
    private List<Track> tracks;
}
