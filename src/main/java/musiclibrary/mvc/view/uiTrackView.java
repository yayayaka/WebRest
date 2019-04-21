package musiclibrary.mvc.view;

import musiclibrary.entities.Album;
import musiclibrary.entities.Entity;
import musiclibrary.entities.Track;
import musiclibrary.mvc.controller.GenericController;

import java.util.List;

public class uiTrackView {
    private GenericController controller;

    public uiTrackView(GenericController controller) {
        this.controller = controller;
    }

    public void add(Entity entity) {
        controller.add(entity);
    }

    public void remove(int id) {
        controller.del(id);
    }

    public void replace(Entity entity) {
        controller.replace(entity);
    }

    public Track get(int id) {
        return (Track) controller.get(id);
    }

    public List<Track> getAll() {
        return controller.getAll();
    }
}
