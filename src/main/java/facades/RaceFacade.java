package facades;

import dtos.RaceDTO;
import dtos.RenameMeDTO;
import entities.Race;
import entities.RenameMe;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class RaceFacade {

    private static RaceFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private RaceFacade() {}
    

    public static RaceFacade getRaceFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RaceFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }



    public List<RaceDTO> getAllRaces(){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Race> query = em.createQuery("SELECT r FROM Race r", Race.class);
            List<Race> races = query.getResultList();
            return RaceDTO.getDTOs(races);
        } finally {
            em.close();
        }
    }

    public RaceDTO getRaceByName(String name){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Race> query = em.createQuery("SELECT r FROM Race r where r.name =:name ", Race.class);
            query.setParameter("name", name);
            Race race = query.getSingleResult();
            return new RaceDTO(race);
        } finally {
            em.close();
        }
    }

    public List<RaceDTO> showCarsByRace(String race){
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("SELECT c FROM Race r JOIN r.carList c WHERE r.name = :name", Race.class);
            query.setParameter("name", race);
            List<Race> r = query.getResultList();
            return RaceDTO.getDTOs(r);
        } finally {
            em.close();
        }
    }


    
    public static void main(String[] args) {

    }

}
