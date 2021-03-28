package ru.shark.home.l2info.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Component
@Produces("application/json")
@Consumes("application/json")
@Path("/weapon")
public class WeaponEndpoint {

    @POST
    @Path("/list")
    public Response list() {
        return Response.ok().build();
    }
}
