package musiclibrary.rest.tracklist;

import musiclibrary.entities.TrackList;
import musiclibrary.mvc.controller.TrackListController;
import musiclibrary.mvc.model.modelswithmorphia.TrackListDBModel;
import musiclibrary.mvc.view.uiTrackListFacade;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tracklists")
public class TrackListRest {
    private uiTrackListFacade view;

    public TrackListRest() {
        view = new uiTrackListFacade(new TrackListController(new TrackListDBModel()));
    }

    @GET
    @Path("/get/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TrackList> getAllTrackListsInJSON() {
        return view.getAll();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response consumeJSON(TrackList trackList) {
        view.add(trackList);
        String output = trackList.toString();
        System.out.println(trackList);
        return Response.status(200).entity(output).build();
    }

    @DELETE
    @Path("/del/{id}")
    public Response delTrackList(@PathParam("id") String id) {
        try {
            view.remove(Integer.parseInt(id));
            return Response.status(200).build();
        } catch (NumberFormatException e) {
            return Response.status(404).build();
        }
    }
}
