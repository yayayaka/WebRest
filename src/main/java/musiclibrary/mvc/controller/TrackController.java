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
    public List<Track> getAll() {
        return container.getItems();
    }
}
//
//
//package musiclibrary.mvc.controller;
//
//import com.google.inject.Inject;
//import com.google.inject.Singleton;
//import musiclibrary.entities.Artist;
//import musiclibrary.entities.Genre;
//import musiclibrary.entities.Track;
//import musiclibrary.mvc.model.Model;
//import musiclibrary.mvc.view.Listener;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.HashMap;
//
//@Singleton
//public class TrackController extends GenericController<Track>{
//
//    private TrackController() {
//    }
//
//    @Inject
//    public TrackController(Model<Track> trackContainer) {
//        this.container = trackContainer;
//    }
//
//    public int add(String name, Artist artist, double trackLenght, Genre genre)  {
//        int id=0;
//        id=getNextId();
//        Track track = new Track(id, name, artist, trackLenght, genre);
//        super.container.put(id,track);
//        return id;
//    }
//
//    public void change(int changedTrackId, String name, Artist artist, double trackLenght, Genre genre) throws InterruptedException {
//        try {
//            super.container.remove(changedTrackId);
//            super.container.put(changedTrackId,new Track(changedTrackId,name,artist,trackLenght,genre));
//        } catch (Exception e) {
//            throw new RuntimeException("Cant change track",e);
//        }
//    }
//}
