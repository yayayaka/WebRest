package musiclibrary.mvc.controller;

import com.google.inject.Inject;
import musiclibrary.entities.Album;
import musiclibrary.mvc.model.Model;

import java.util.List;

public class AlbumController extends  GenericController<Album> {

    private AlbumController() {
    }

    @Inject
    public AlbumController(Model<Album> albumContainer) {
        this.container = albumContainer;
    }

    @Override
    public void add(Album album) {
        container.put(-1, album);
    }

    @Override
    public void del(int id) {
        container.remove(id);
    }

    @Override
    public void replace(Album album) {
        container.update(album);
    }

    @Override
    public Album get(int id) {
        return container.getItem(id);
    }

    @Override
    public List<Album> getAll() {
        return container.getItems();
    }
}
