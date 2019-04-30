package musiclibrary.rest.album;

import musiclibrary.entities.Album;
import musiclibrary.mvc.controller.AlbumController;
import musiclibrary.mvc.model.modelswithmorphia.AlbumDBModel;
import musiclibrary.mvc.view.uiAlbumFacade;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/albums")
public class AlbumRest {
    private uiAlbumFacade view;

    public AlbumRest() {
        view = new uiAlbumFacade(new AlbumController(new AlbumDBModel()));
    }

    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Album> getAllArtistsInJSON() {
        return view.getAll();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consumeJSON(Album album) {
        view.add(album);
        String output = album.toString();
        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Path("/del/{id}")
    public Response delAlbum(@PathParam("id") String id) {
        try {
            view.remove(Integer.parseInt(id));
            return Response.status(200).build();
        } catch (NumberFormatException e) {
            return Response.status(404).build();
        }
    }
}
