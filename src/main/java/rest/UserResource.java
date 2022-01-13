package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RaceDTO;
import facades.RaceFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;

//Todo Remove or change relevant parts before ACTUAL use
@Path("user")
public class UserResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
       
    private static final RaceFacade FACADE =  RaceFacade.getRaceFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @Path("race/all")
    @GET
    //@RolesAllowed("user")
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllRaces() {
        return GSON.toJson(FACADE.getAllRaces());
    }

    @Path("race/{raceName}")
    @GET
    //@RolesAllowed({"user", "admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public String getAllBoatOwner(@PathParam("raceName") String raceName) {
        RaceDTO rdto = FACADE.getRaceByName(raceName);
        return GSON.toJson(rdto);
    }

    @Path("race/{racename}")
    @GET
    //@RolesAllowed({"user", "admin"})
    @Produces({MediaType.APPLICATION_JSON})
    public String getCarsByRace(@PathParam("racename") String raceName) {
        List<RaceDTO> rdtoList = FACADE.showCarsByRace(raceName);
        return GSON.toJson(rdtoList);
    }
}
