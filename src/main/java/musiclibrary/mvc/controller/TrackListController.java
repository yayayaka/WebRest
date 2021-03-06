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

public class TrackListController extends  GenericController<TrackList> {

    private TrackListController() {
    }

    @Inject
    public TrackListController(Model<TrackList> trackListContainer) {
        this.container = trackListContainer;
    }

    @Override
    public void add(TrackList trackList) {
        container.put(-1, trackList);
    }

    @Override
    public void del(int id) {
        container.remove(id);
    }

    @Override
    public void replace(TrackList trackList) {
        container.update(trackList);
    }

    @Override
    public TrackList get(int id) {
        return container.getItem(id);
    }

    @Override
    public List<TrackList> getAll() {
        return container.getItems();
    }
}


//package musiclibrary.mvc.controller;
//
//import com.google.common.collect.ImmutableList;
//import com.google.inject.Inject;
//import com.google.inject.Singleton;
//import musiclibrary.entities.*;
//import musiclibrary.entities.TrackList;
//import musiclibrary.mvc.model.Model;
//import musiclibrary.mvc.view.Listener;
//
//@Singleton
//public class TrackListController extends GenericController<TrackList> {
//    private TrackListController() {
//    }
//
//    @Inject
//    public TrackListController(Model<TrackList> TrackListContainer) {
//        this.container = TrackListContainer;
//    }
//
//    public int add(Album album, ImmutableList<Track> tracks) throws InterruptedException {
//        int id=0;
//        id=getNextId();
//        TrackList TrackList = new TrackList(id, album,tracks);
//        container.put(id,TrackList);
//        return id;
//    }
//
//    public void change(int changedTrackListId, Album album, ImmutableList<Track> tracks) throws InterruptedException {
//        try {
//            TrackList TrackList = new TrackList(changedTrackListId, album,tracks);
//            container.remove(changedTrackListId);
//            container.put(changedTrackListId,TrackList);
//        } catch (Exception e) {
//            throw new RuntimeException("Cant change TrackList",e);
//        }
//    }
//}
