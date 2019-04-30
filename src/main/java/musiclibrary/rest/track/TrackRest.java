package musiclibrary.rest.track;

import musiclibrary.entities.Track;
import musiclibrary.mvc.controller.TrackController;
import musiclibrary.mvc.model.modelswithmorphia.TrackDBModel;
import musiclibrary.mvc.view.uiTrackFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/tracks")
public class TrackRest {
    private uiTrackFacade view;

    public TrackRest() {
        view = new uiTrackFacade(new TrackController(new TrackDBModel()));
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

    @GET
    @Path("get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Track getTrack(@PathParam("id") String id) {
        try {
            return view.get(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @POST
    @Path("/getbyids")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Track> getTracksByIds(List<EntityId> ids) {
        int[] intIds = new int[ids.size()];
        List<Track> tracks = new ArrayList<Track>();
        for (int pointer = 0; pointer < ids.size(); pointer++) {
            intIds[pointer] = ids.get(pointer).getId();
            tracks.add(view.get(ids.get(pointer).getId()));
        }
        for (Track track : tracks) {
            System.out.println(track);
        }
        return tracks;
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
