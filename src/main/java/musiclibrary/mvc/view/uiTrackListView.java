package musiclibrary.mvc.view;

import musiclibrary.entities.Entity;
import musiclibrary.entities.Track;
import musiclibrary.entities.TrackList;
import musiclibrary.mvc.controller.GenericController;

import java.util.List;

public class uiTrackListView {
    private GenericController controller;

    public uiTrackListView(GenericController controller) {
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

    public TrackList get(int id) {
        return (TrackList) controller.get(id);
    }

    public List<TrackList> getAll() {
        return controller.getAll();
    }
}
