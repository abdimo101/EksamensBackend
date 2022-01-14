package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CarDTO;
import dtos.RaceDTO;
import facades.RaceFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("race")
public class UserResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final RaceFacade FACADE =  RaceFacade.getRaceFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @Path("all")
    @GET
    //@RolesAllowed("user")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllRaces() {
        return GSON.toJson(FACADE.getAll());
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String createRace(String race){
        RaceDTO rdto = GSON.fromJson(race, RaceDTO.class);
        RaceDTO newrdto = FACADE.createRace(rdto);
        return GSON.toJson(newrdto);
    }

    @Path("{racename}")
    @GET
    //@RolesAllowed({"user", "admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarsByRace(@PathParam("racename") String raceName) {
        List<CarDTO> cdtoList = FACADE.getCarsByRace(raceName);
        return GSON.toJson(cdtoList);
    }

    @Path("drivers/{raceName}")
    @GET
    @Produces("application/json")
    public String getDriversByName(@PathParam("raceName")String name) {
        return GSON.toJson(FACADE.getCarsByRace(name));
    }
}
