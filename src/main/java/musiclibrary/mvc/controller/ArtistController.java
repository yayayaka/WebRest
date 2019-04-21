package musiclibrary.mvc.controller;

import com.google.inject.*;
import musiclibrary.entities.Album;
import musiclibrary.entities.Artist;
import musiclibrary.entities.Genre;
import musiclibrary.entities.Artist;
import musiclibrary.mvc.model.Model;
import musiclibrary.mvc.view.Listener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArtistController extends  GenericController<Artist> {

    private ArtistController() {
    }

    @Inject
    public ArtistController(Model<Artist> artistContainer) {
        this.container = artistContainer;
    }

    @Override
    public void add(Artist artist) {
        container.put(-1, artist);
    }

    @Override
    public void del(int id) {
        container.remove(id);
    }

    @Override
    public void replace(Artist artist) {
        container.update(artist);
    }

    @Override
    public Artist get(int id) {
        return container.getItem(id);
    }

    @Override
    public List<Artist> getAll() {
        return container.getItems();
    }
}
