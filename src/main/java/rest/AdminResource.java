package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RaceDTO;
import facades.SharedFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
@Path("admin")
public class AdminResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final SharedFacade FACADE =  SharedFacade.getSharedFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;


    @POST
    @RolesAllowed("admin")
    @Consumes("application/json")
    @Produces("application/json")
    public String createRace(String race){
        RaceDTO rdto = GSON.fromJson(race, RaceDTO.class);
        RaceDTO newrdto = FACADE.createRace(rdto);
        return GSON.toJson(newrdto);
    }

    @Path("delete/car/{id}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public String deleteCar(@PathParam("id") int id) throws EntityNotFoundException {
        return FACADE.deleteCar(id);
    }
}
