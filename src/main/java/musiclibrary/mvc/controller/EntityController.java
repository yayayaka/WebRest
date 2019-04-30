package musiclibrary.mvc.controller;

import com.google.inject.Inject;
import musiclibrary.entities.Entity;
import musiclibrary.entities.TrackList;
import musiclibrary.mvc.model.Model;

import java.util.List;

public class EntityController<T extends Entity> extends GenericController<T> {
    private EntityController() {
    }

    @Inject
    public EntityController(Model<T> EntityContainer) {
        this.container = EntityContainer;
    }

    @Override
    public void add(T entity) {
        container.put(-1, entity);
    }

    @Override
    public void del(int id) {
        container.remove(id);
    }

    @Override
    public void replace(T entity) {
        container.update(entity);
    }

    @Override
    public T get(int id) {
        return container.getItem(id);
    }

    @Override
    public List<T> getAll() {
        return container.getItems();
    }

    @Override
    public List<T> getByIds(int[] ids) {
        return container.getByIds(ids);
    }
}
