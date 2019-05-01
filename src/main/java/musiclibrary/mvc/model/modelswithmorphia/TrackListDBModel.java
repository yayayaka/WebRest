package musiclibrary.mvc.model.modelswithmorphia;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import musiclibrary.entities.TrackList;
import musiclibrary.entities.User;
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
        put(new TrackList(getNextId(), trackList.getName(), trackList.getTracks()));
    }


    public boolean remove(int id) {
        Query<TrackList> query = ds.createQuery(TrackList.class)
                .field("_id").equal(id);
        TrackList trackList = query.get();
        cascadeTrackListDelete(trackList);
        WriteResult result = ds.delete(query);
        return result.wasAcknowledged();
    }

    public void cascadeTrackListDelete(TrackList trackList) {
        Query<User> userCascadeQuery = ds.createQuery(User.class);
        List<User> userList = userCascadeQuery.asList();
        for (User user : userList) {
            List<TrackList> trackLists = user.getTrackLists();
            boolean boo = true;
            while (boo) {
                boo = trackLists.remove(trackList);
            }
        }
    }
    
    public TrackList getItem(int id){
        Query<TrackList> query = ds.createQuery(TrackList.class)
                .field("_id").equal(id);
        return query.get();
    }

    public List<TrackList> getItems() {
        Query<TrackList> query = ds.createQuery(TrackList.class);
        return query.asList();
    }

    public void update(TrackList trackList) {
        Query<TrackList> query = ds.createQuery(TrackList.class)
                .field("_id").equal(trackList.getId());
        UpdateOperations<TrackList> updateOperation = ds.createUpdateOperations(TrackList.class)
                .set("name", trackList.getName())
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