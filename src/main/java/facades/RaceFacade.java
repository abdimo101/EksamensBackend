package facades;

import dtos.CarDTO;
import dtos.DriverDTO;
import dtos.RaceDTO;
import dtos.RenameMeDTO;
import entities.Car;
import entities.Driver;
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



    public List<RaceDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery query = em.createQuery("Select r from Race r", Race.class);
            List<Race> re = query.getResultList();
            return RaceDTO.getDTOs(re);
        } finally{
            em.close();
        }
    }

    public List<DriverDTO> getDriversByRace(String name) {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery query = em.createQuery("Select c.driverList from Race r JOIN r.carList c Where r.name = :name", Driver.class);
            query.setParameter("name", name);
            List<Driver> de = query.getResultList();
            return DriverDTO.getDtos(de);
        }  finally{
            em.close();
        }
    }

    public List<CarDTO> getCarsByRace(String name) {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery query = em.createQuery("Select c from Race r JOIN r.carList c Where r.name = :name", Car.class);
            query.setParameter("name", name);
            List<Car> ce = query.getResultList();
            return CarDTO.getCarDTO(ce);
        }  finally{
            em.close();
        }
    }

    public RaceDTO createRace(RaceDTO racedto) {
        EntityManager em = emf.createEntityManager();
        List<Car> carList = new ArrayList<>();
        List<CarDTO> carDTOList = racedto.getCarList();
        for (CarDTO carDTO : carDTOList) {
            Car car1 = new Car(carDTO.getName(),carDTO.getBrand(), carDTO.getMake(), carDTO.getYear(), carDTO.getDriverList());
            carList.add(car1);
        }
        Race race = new Race(racedto.getName(), racedto.getLocation(), carList);
        try {
            em.getTransaction().begin();
            em.persist(race);
            em.getTransaction().commit();
            return new RaceDTO(race);
        } finally {
            em.close();
        }
    }


    
    public static void main(String[] args) {

    }

}
