package musiclibrary.mvc.controller;

import com.google.inject.*;
import musiclibrary.entities.*;
import musiclibrary.entities.Artist;
import musiclibrary.mvc.model.Model;
import musiclibrary.mvc.view.Listener;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrackController extends  GenericController<Track> {

    private TrackController() {
    }

    @Inject
    public TrackController(Model<Track> trackContainer) {
        this.container = trackContainer;
    }

    @Override
    public void add(Track track) {
        container.put(-1, track);
    }

    @Override
    public void del(int id) {
        container.remove(id);
    }

    @Override
    public void replace(Track track) {
        container.update(track);
    }

    @Override
    public Track get(int id) {
        return container.getItem(id);
    }

    @Override
    public List<Track> getByIds(int[] ids) {
        return container.getByIds(ids);
    }

    @Override
    public List<Track> getAll() {
        return container.getItems();
    }
}
