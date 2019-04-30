package musiclibrary.rest.genre;

import musiclibrary.entities.Genre;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/genres")
public class GenreRest {
    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Genre[] getAllArtistsInJSON() {
        return Genre.values();
    }
}
