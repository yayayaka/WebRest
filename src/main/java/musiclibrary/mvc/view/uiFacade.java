package musiclibrary.mvc.view;

import musiclibrary.entities.Entity;
import musiclibrary.mvc.controller.GenericController;

import java.util.List;

public class uiFacade {
    private GenericController controller;

    public uiFacade(GenericController controller) {
        this.controller = controller;
    }

    public void add(Entity entity) {
        controller.add(entity);
    }

    public void remove(int id) {
        controller.del(id);
    }

    public void replace(Entity entity) {
        controller.replace(entity);
    }

    public Entity get(int id) {
        return (Entity) controller.get(id);
    }

    public List<Entity> getByIds(int[] ids) {
        return (List<Entity>) controller.getByIds(ids);
    }

    public List<Entity> getAll() {
        return controller.getAll();
    }
}
