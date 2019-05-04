package musiclibrary.rest.user;

import musiclibrary.entities.User;
import musiclibrary.mvc.controller.UserController;
import musiclibrary.mvc.model.modelswithmorphia.UserDBModel;
import musiclibrary.mvc.view.uiUserFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/users")
public class UserRest {
    private uiUserFacade view;

    public UserRest() {
        view = new uiUserFacade(new UserController(new UserDBModel()));
    }

    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
//    public List<User> getAllUsersInJSON() {
//        return view.getAll();
//    }
    public Response getAllUsersInJSON() {
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(view.getAll())
                .build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consumeJSON(User user) {
        view.add(user);
        String output = user.toString();
        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Path("/del/{id}")
    public Response delUser(@PathParam("id") String id) {
        try {
            view.remove(Integer.parseInt(id));
            return Response.status(200).build();
        } catch (NumberFormatException e) {
            return Response.status(404).build();
        }
    }
}
