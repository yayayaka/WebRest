package musiclibrary.rest.genre;

import musiclibrary.entities.Album;
import musiclibrary.entities.Genre;
import musiclibrary.mvc.controller.AlbumController;
import musiclibrary.mvc.model.modelswithmorphia.AlbumDBModel;
import musiclibrary.mvc.view.uiAlbumView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/genres")
public class GenreRest {
    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Genre[] getAllArtistsInJSON() {
        return Genre.values();
    }
}
