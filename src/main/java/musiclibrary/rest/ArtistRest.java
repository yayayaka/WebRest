package musiclibrary.rest;

import musiclibrary.entities.Artist;
import musiclibrary.mvc.controller.ArtistController;
import musiclibrary.mvc.model.modelswithmorphia.ArtistDBModel;
import musiclibrary.mvc.view.uiArtistFacade;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/artists")
public class ArtistRest {
    private uiArtistFacade view;

    public ArtistRest() {
        view = new uiArtistFacade(new ArtistController(new ArtistDBModel()));
    }

    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllArtistsInJson() {
        return Response.status(200)
                .entity(view.getAll())
//                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consumeJSON(Artist artist) {
        view.add(artist);
        String output = artist.toString();
        return Response.status(200)
                .entity(output)
//                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

//    @OPTIONS
//    @Path("/add")
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response consumeJSON1(Artist artist) {
//        view.add(artist);
//        String output = artist.toString();
//        return Response.status(200)
//                .header("Access-Control-Allow-Origin", "*")
//                .build();
//    }

    @DELETE
    @Path("/del/{id}")
    public Response delArtist(@PathParam("id") String id) {
        try {
            view.remove(Integer.parseInt(id));
            return Response
                    .status(200)
//                    .header("Access-Control-Allow-Origin", "*")
//                    .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//                    .header("Access-Control-Allow-Credentials", "true")
//                    .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                    .build();
        } catch (NumberFormatException e) {
            return Response
                    .status(404)
//                    .header("Access-Control-Allow-Origin", "*")
                    .build();
        }
    }
}
