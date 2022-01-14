package facades;

import dtos.CarDTO;
import dtos.DriverDTO;
import dtos.RaceDTO;
import entities.Car;
import entities.Driver;
import entities.Race;

import javax.persistence.*;
import javax.ws.rs.WebApplicationException;
import java.util.ArrayList;
import java.util.List;

public class SharedFacade {

    private static SharedFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private SharedFacade() {}
    

    public static SharedFacade getSharedFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SharedFacade();
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

    public String deleteCar(int id) {
        EntityManager em = getEntityManager();
        Car car = em.find(Car.class, id);
        try {
            if (car == null) {
                throw new EntityNotFoundException("Could not find any car with id: " + id);
            }
            em.getTransaction().begin();
            em.remove(car);
            em.getTransaction().commit();
        } catch (EntityNotFoundException enfe) {
            throw new WebApplicationException(enfe.getMessage(), 404);
        } finally {
            em.close();
        }
        return "Car with id: " + id + " was successfully deleted";
    }


    
    public static void main(String[] args) {

    }

}
