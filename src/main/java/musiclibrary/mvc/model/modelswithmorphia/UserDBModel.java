package musiclibrary.mvc.model.modelswithmorphia;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import musiclibrary.entities.User;
import musiclibrary.mvc.model.Model;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.FindOptions;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.List;

import static musiclibrary.dbworks.dbconstants.DBconstants.DBNAME;

public class UserDBModel extends Model<User> {
    Morphia morphia;
    MongoClient mongoClient;
    Datastore ds;

    public UserDBModel() {
        morphia = new Morphia();
        mongoClient = new MongoClient();
        ds = morphia.createDatastore(mongoClient, DBNAME);
    }

    public void put(User user) {
        ds.save(user);
    }

    public void put(int id, User user) {
        put(new User(getNextId(), user.getName(), user.getTrackLists()));
    }


    public boolean remove(int id) {
        Query<User> query = ds.createQuery(User.class)
                .field("_id").equal(id);
        WriteResult result = ds.delete(query);
        return result.wasAcknowledged();
    }

    public boolean remove(String name) {
        Query<User> query = ds.createQuery(User.class)
                .field("name").equal(name);
        WriteResult result = ds.delete(query);
        return result.wasAcknowledged();
    }

    public User getItem(String userName){
        Query<User> query = ds.createQuery(User.class)
                .field("name").equal(userName);
        return query.get();
    }

    public User getItem(int id){
        Query<User> query = ds.createQuery(User.class)
                .field("_id").equal(id);
        return query.get();
    }

    public List<User> getItems() {
        Query<User> query = ds.createQuery(User.class);
        return query.asList();
    }

    public List<User> getItems(String userName) {
        Query<User> query = ds.createQuery(User.class)
                .field("name").equal(userName);
        return query.asList();
    }

    public void update(User user) {
        Query<User> query = ds.createQuery(User.class)
                .field("_id").equal(user.getId());
        UpdateOperations<User> updateOperation = ds.createUpdateOperations(User.class)
                .set("name", user.getName())
                .set("trackLists", user.getTrackLists());
        ds.update(query, updateOperation);
    }

    public int getNextId() {
        Query<User> query = ds.createQuery(User.class);
        if (query.count() > 0) {
            FindOptions findOptions = new FindOptions().limit(1);
            return ds.createQuery(User.class)
                    .order("-_id").get(findOptions).getId() + 1;
        }
        return 0;
    }
}
