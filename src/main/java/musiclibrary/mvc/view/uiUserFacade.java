package musiclibrary.mvc.view;

import musiclibrary.entities.Entity;
import musiclibrary.entities.Track;
import musiclibrary.entities.User;
import musiclibrary.mvc.controller.GenericController;

import java.util.List;

public class uiUserFacade {
    private GenericController controller;

    public uiUserFacade(GenericController controller) {
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

    public User get(int id) {
        return (User) controller.get(id);
    }

    public List<User> getAll() {
        return controller.getAll();
    }
}
