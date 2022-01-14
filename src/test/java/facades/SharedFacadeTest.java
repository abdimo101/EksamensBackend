package facades;

import dtos.RaceDTO;
import entities.Car;
import entities.Driver;
import entities.Race;
import entities.RenameMe;
import org.apache.http.impl.bootstrap.HttpServer;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class SharedFacadeTest {

    private static EntityManagerFactory emf;
    private static SharedFacade facade;



    public SharedFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = SharedFacade.getSharedFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("RenameMe.deleteAllRows").executeUpdate();
            em.persist(new RenameMe("Some txt", "More text"));
            em.persist(new RenameMe("aaa", "bbb"));
            List<Driver> driverList = new ArrayList<>();
            List<Car> carList = new ArrayList<>();
            List<Car> carList1 = new ArrayList<>();
            List<Car> carList2 = new ArrayList<>();
            Car car = new Car("carnametest1", "brandtest1", "maketest1", 2015, driverList);
            Car car1 = new Car("carnametest2", "brandtest2", "maketest2", 2015, driverList);
            Car car2 = new Car("carnametest3", "brandtest3", "maketest3", 2015, driverList);

            carList.add(car);
            carList1.add(car1);
            carList2.add(car2);
            Race race = new Race("racenametest1","locationtest1", carList);
            Race race1 = new Race("racenametest2","locationtest2", carList1);
            Race race2 = new Race("racenametest3","locationtest3", carList2);
            em.persist(race);
            em.persist(race1);
            em.persist(race2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }


    //@Test
    public void testGetAll() throws Exception {
        assertEquals(3, facade.getAll().size(), "Expects three rows in the database");
    }

    @Test
    public void testGetCarsByRace() throws Exception {
        assertEquals("brandtest2", facade.getCarsByRace("racenametest2").get(0).getBrand());
    }

    @Test
    public void testCreateRace() throws Exception {
        List<Driver> driverList = new ArrayList<>();
        List<Car> carList = new ArrayList<>();
        Car car = new Car("carnametest4", "brandtest4", "maketest4", 2015, driverList);
        carList.add(car);
        Race race = new Race("racenametest4","locationtest4", carList);
        RaceDTO raceDTO = new RaceDTO(race);
        facade.createRace(raceDTO);
        assertEquals("carnametest4", facade.getCarsByRace("racenametest4").get(0).getName());
    }


    

}
