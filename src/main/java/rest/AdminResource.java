package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.RaceDTO;
import facades.RaceFacade;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
@Path("admin")
public class AdminResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final RaceFacade FACADE =  RaceFacade.getRaceFacade(EMF);
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
}
