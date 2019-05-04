package musiclibrary.rest.genre;

import musiclibrary.entities.Genre;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/genres")
public class GenreRest {
    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
//    public Genre[] getAllArtistsInJSON() {
//        return Genre.values();
//    }
    public Response getAllArtistsInJSON() {
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .entity(Genre.values())
                .build();
    }
}
