package ru.shark.home.l2info.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.dto.RaceDto;
import ru.shark.home.l2info.services.RaceService;
import ru.shark.home.l2info.services.dto.PageRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Component
@Produces("application/json")
@Consumes("application/json")
@Path("/race")
public class RaceEndpoint {

    private RaceService raceService;

    @POST
    @Path("/list")
    public Response getList(PageRequest request) {
        return Response.ok(raceService.getList(request)).build();
    }

    @POST
    @Path("/save")
    public Response save(RaceDto dto) {
        return Response.ok(raceService.save(dto)).build();
    }

    @POST
    @Path("/{id}/delete")
    public Response delete(@PathParam("id") Long id) {
        return Response.ok(raceService.delete(id)).build();
    }

    @Autowired
    public void setRaceService(RaceService raceService) {
        this.raceService = raceService;
    }
}
