package ru.shark.home.l2info.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shark.home.l2info.dao.dto.WeaponDto;
import ru.shark.home.l2info.services.WeaponService;
import ru.shark.home.l2info.services.dto.PageRequest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Component
@Produces("application/json")
@Consumes("application/json")
@Path("/weapon")
public class WeaponEndpoint {

    private WeaponService weaponService;

    @POST
    @Path("/list")
    public Response getList(PageRequest request) {
        return Response.ok(weaponService.getList(request)).build();
    }

    @POST
    @Path("/save")
    public Response save(WeaponDto dto) {
        return Response.ok(weaponService.save(dto)).build();
    }

    @POST
    @Path("/{id}/delete")
    public Response delete(@PathParam("id") Long id) {
        return Response.ok(weaponService.delete(id)).build();
    }

    @Autowired
    public void setWeaponService(WeaponService weaponService) {
        this.weaponService = weaponService;
    }
}
