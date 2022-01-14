/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

import java.util.ArrayList;
import java.util.List;


public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        //FacadeExample fe = FacadeExample.getFacadeExample(emf);
        /*
        fe.create(new RenameMeDTO(new RenameMe("First 1", "Last 1")));
        fe.create(new RenameMeDTO(new RenameMe("First 2", "Last 2")));
        fe.create(new RenameMeDTO(new RenameMe("First 3", "Last 3")));
         */

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Car> carList = new ArrayList<>();
        List<Driver> driverList = new ArrayList<>();
        Car car = new Car("Audiname", "Audi", "A4", 2015, driverList);
        Car car1 = new Car("Fordname", "Ford", "Fiesta", 2005, driverList);
        Driver driver = new Driver("Abdi", 1999, "male", car);
        Driver driver1 = new Driver("Zamad", 1980, "male", car1);
        driverList.add(driver);
        driverList.add(driver1);
        carList.add(car);
        carList.add(car1);

        Race race = new Race("Formel 1","London", carList);
        Race race1 = new Race("Drifting","København");
        em.persist(race);
        em.persist(race1);

        em.getTransaction().commit();
        em.close();
    }

    public static void main(String[] args) {
        populate();
    }
}
