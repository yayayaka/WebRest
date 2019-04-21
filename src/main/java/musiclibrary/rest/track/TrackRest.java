package musiclibrary.rest.track;

import musiclibrary.entities.Track;
import musiclibrary.mvc.controller.TrackController;
import musiclibrary.mvc.model.modelswithmorphia.TrackDBModel;
import musiclibrary.mvc.view.uiTrackView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tracks")
public class TrackRest {
    private uiTrackView view;

    public TrackRest() {
        view = new uiTrackView(new TrackController(new TrackDBModel()));
    }

    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Track> getAllTracksInJSON() {
        return view.getAll();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consumeJSON(Track track) {
        view.add(track);
        String output = track.toString();
        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Path("/del/{id}")
    public Response delTrack(@PathParam("id") String id) {
        try {
            view.remove(Integer.parseInt(id));
            return Response.status(200).build();
        } catch (NumberFormatException e) {
            return Response.status(404).build();
        }
    }
}
