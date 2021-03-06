package musiclibrary.mvc.model.modelswithmorphia;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import musiclibrary.entities.*;
import musiclibrary.mvc.model.Model;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.ArrayList;
import java.util.List;

import static musiclibrary.dbworks.dbconstants.DBconstants.DBNAME;

public class TrackDBModel extends Model<Track> {
    Morphia morphia;
    MongoClient mongoClient;
    Datastore ds;

    public TrackDBModel() {
        morphia = new Morphia();
        mongoClient = new MongoClient();
        ds = morphia.createDatastore(mongoClient, DBNAME);
    }

    public void put(Track track) {
        ds.save(track);
    }

    public void put(int id, Track track) {
        put(new Track(getNextId(), track.getName(), track.getArtist(),
                track.getTrackLength(), track.getGenre()));
    }


    public boolean remove(int id) {
        Query<Track> query = ds.createQuery(Track.class)
                .field("_id").equal(id);
        Track track = query.get();

//        Query<Album> albumCascadeQuery = ds.createQuery(Album.class);
//        List<Album> albumList = albumCascadeQuery.asList();
//        for (Album album : albumList) {
//            List<Track> tracks = album.getTracks();
//            boolean boo = true;
//            while (boo) {
//                boo = tracks.remove(track);
//            }
//        }

        trackCascadeDelete(Album.class, track);
        trackCascadeDelete(TrackList.class, track);

        WriteResult result = ds.delete(query);
        return result.wasAcknowledged();
    }

    public <T extends TrackCollection> void trackCascadeDelete(Class<T> trackCollectionClass, Track track) {
        Query<T> albumCascadeQuery = ds.createQuery(trackCollectionClass);
        List<T> albumList = albumCascadeQuery.asList();
        for (TrackCollection album : albumList) {
            List<Track> tracks = album.getTracks();
            boolean boo = true;
            while (boo) {
                boo = tracks.remove(track);
            }
        }
    }

    public boolean remove(String name) {
        Query<Track> query = ds.createQuery(Track.class)
                .field("name").equal(name);
        WriteResult result = ds.delete(query);
        return result.wasAcknowledged();
    }

    public Track getItem(String trackName){
//        Query q = ds.find(Track.class).field("artist").equal(ds.get(Artist.class, new ObjectId("5693e72244ae9fe4803a4520")));
        Query<Track> query = ds.createQuery(Track.class)
                .field("name").equal(trackName);
//        query.get().getArtist();
        return query.get();
    }

    public Track getItem(int id){
        Query<Track> query = ds.createQuery(Track.class)
                .field("_id").equal(id);
        return query.get();
    }

    public List<Track> getByIds(int[] ids) {
        List<Track> returnTracks = new ArrayList<Track>();
        for (int id : ids) {
            Query<Track> query = ds.createQuery(Track.class)
                    .field("_id").equal(ids);
            returnTracks.add(query.get());
        }
        return returnTracks;
    }

    public List<Track> getItems() {
        Query<Track> query = ds.createQuery(Track.class);
        return query.asList();
    }

    public List<Track> getItems(String trackName) {
        Query<Track> query = ds.createQuery(Track.class)
                .field("name").equal(trackName);
        return query.asList();
    }

    public void update(Track track) {
        Query<Track> query = ds.createQuery(Track.class)
                .field("_id").equal(track.getId());
        UpdateOperations<Track> updateOperation = ds.createUpdateOperations(Track.class)
                .set("name", track.getName())
                .set("artist", track.getArtist())
                .set("trackLenght", track.getTrackLength())
                .set("genre", track.getGenre());
        ds.update(query, updateOperation);
    }

    public int getNextId() {
        Query<Track> query = ds.createQuery(Track.class);
        if (query.count() > 0) {
            FindOptions findOptions = new FindOptions().limit(1);
            return ds.createQuery(Track.class)
                    .order("-_id").get(findOptions).getId() + 1;
        }
        return 0;
    }
}
