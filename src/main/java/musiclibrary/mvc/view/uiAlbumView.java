package musiclibrary.mvc.view;

import musiclibrary.entities.Album;
import musiclibrary.entities.Entity;
import musiclibrary.mvc.controller.GenericController;
import java.util.List;

public class uiAlbumView {
    private GenericController controller;

    public uiAlbumView(GenericController controller) {
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

    public Album get(int id) {
        return (Album) controller.get(id);
    }

    public List<Album> getAll() {
        return controller.getAll();
    }
}
