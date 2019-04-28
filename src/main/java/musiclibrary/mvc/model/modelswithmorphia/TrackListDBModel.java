package musiclibrary.mvc.model.modelswithmorphia;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import musiclibrary.entities.TrackList;
import musiclibrary.mvc.model.Model;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

import static musiclibrary.dbworks.dbconstants.DBconstants.DBNAME;

public class TrackListDBModel extends Model<TrackList> {
    Morphia morphia;
    MongoClient mongoClient;
    Datastore ds;

    public TrackListDBModel() {
        morphia = new Morphia();
        mongoClient = new MongoClient();
        ds = morphia.createDatastore(mongoClient, DBNAME);
    }

    public void put(TrackList trackList) {
        ds.save(trackList);
    }

    public void put(int id, TrackList trackList) {
        put(new TrackList(getNextId(), trackList.getAlbum(), trackList.getTracks()));
    }


    public boolean remove(int id) {
        Query<TrackList> query = ds.createQuery(TrackList.class)
                .field("_id").equal(id);
        WriteResult result = ds.delete(query);
        return result.wasAcknowledged();
    }

//    public boolean remove(String name) {
//        Query<Track> query = ds.createQuery(Track.class)
//                .field("name").equal(name);
//        WriteResult result = ds.delete(query);
//        return result.wasAcknowledged();
//    }

//    public TrackList getItem(String trackName){
//        Query q = ds.find(Track.class).field("artist").equal(ds.get(Artist.class, new ObjectId("5693e72244ae9fe4803a4520")));
//        Query<Track> query = ds.createQuery(Track.class)
//                .field("name").equal(trackName);
//        query.get().getArtist();
//        return query.get();
//    }

    public TrackList getItem(int id){
        Query<TrackList> query = ds.createQuery(TrackList.class)
                .field("_id").equal(id);
        return query.get();
    }

    public List<TrackList> getItems() {
        Query<TrackList> query = ds.createQuery(TrackList.class);
        return query.asList();
    }

//    public List<TrackList> getItems(String trackName) {
//        Query<TrackList> query = ds.createQuery(TrackList.class)
//                .field("name").equal(trackName);
//        return query.asList();
//    }

    public void update(TrackList trackList) {
        Query<TrackList> query = ds.createQuery(TrackList.class)
                .field("_id").equal(trackList.getId());
        UpdateOperations<TrackList> updateOperation = ds.createUpdateOperations(TrackList.class)
                .set("album", trackList.getAlbum())
                .set("tracks", trackList.getTracks());
        ds.update(query, updateOperation);
    }

    public int getNextId() {
        Query<TrackList> query = ds.createQuery(TrackList.class);
        if (query.count() > 0) {
            FindOptions findOptions = new FindOptions().limit(1);
            return ds.createQuery(TrackList.class)
                    .order("-_id").get(findOptions).getId() + 1;
        }
        return 0;
    }
}


//package musiclibrary.mvc.model.modelswithmorphia;
//
//import com.google.inject.Guice;
//import com.mongodb.DBObject;
//import com.mongodb.MongoClient;
//import com.mongodb.WriteResult;
//import musiclibrary.entities.Track;
//import musiclibrary.entities.TrackList;
//import org.mongodb.morphia.Datastore;
//import org.mongodb.morphia.Morphia;
//import org.mongodb.morphia.ObjectFactory;
//import org.mongodb.morphia.mapping.DefaultCreator;
//import org.mongodb.morphia.mapping.MappedField;
//import org.mongodb.morphia.mapping.Mapper;
//import org.mongodb.morphia.query.FindOptions;
//import org.mongodb.morphia.query.Query;
//import org.mongodb.morphia.query.UpdateOperations;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import static musiclibrary.dbworks.dbconstants.DBconstants.DBNAME;
//
//public class TrackListDBModel {
//    Morphia morphia;
//    MongoClient mongoClient;
//    Datastore ds;
//
//    public TrackListDBModel() {
//        morphia = new Morphia();
//        mongoClient = new MongoClient();
//        ds = morphia.createDatastore(mongoClient, DBNAME);
//        //morphia.getMapper().getOptions().setObjectFactory(new CustomMorphiaObjectFactory());
//    }
//
//    public void put(TrackList trackList) {
//        ds.save(trackList);
//    }
//
//    public boolean remove(int id) {
//        Query<TrackList> query = ds.createQuery(TrackList.class)
//                .field("_id").equal(id);
//        WriteResult result = ds.delete(query);
//        return result.wasAcknowledged();
//    }
//
//    public boolean remove(String name) {
//        Query<TrackList> query = ds.createQuery(TrackList.class)
//                .field("album.name").equal(name);
//        WriteResult result = ds.delete(query);
//        return result.wasAcknowledged();
//    }
//
////    public TrackList getItem(String trackName){
////        Query<TrackList> query = ds.createQuery(Track.class)
////                .field("name").equal(trackName);
////        return query.get();
////    }
//
//    public TrackList getItem(int id){
//        Query<TrackList> query = ds.createQuery(TrackList.class)
//                .field("_id").equal(id);
//       // query.field("tracks").greaterThan(0);
//        return query.get();
//    }
//
//        public void update(TrackList trackList) {
//        Query<TrackList> query = ds.createQuery(TrackList.class)
//                .field("_id").equal(trackList.getId());
//        UpdateOperations<TrackList> updateOperation = ds.createUpdateOperations(TrackList.class)
//                .set("album", trackList.getAlbum())
//                .set("tracks", trackList.getTracks());
//        ds.update(query, updateOperation);
//    }
//
//    public int getNextId() {
//        Query<TrackList> query = ds.createQuery(TrackList.class);
//        if (query.count() > 0) {
//            FindOptions findOptions = new FindOptions().limit(1);
//            return ds.createQuery(TrackList.class)
//                    .order("-_id").get(findOptions).getId() + 1;
//        }
//        return 0;
//    }
//}