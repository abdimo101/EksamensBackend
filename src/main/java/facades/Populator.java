/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import utils.EMF_Creator;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Driver> driverList = new ArrayList<>();
        List<Car> carList = new ArrayList<>();
        Car car = new Car("nametest1", "brandtest1", "maketest1", 2010, driverList);
        Driver driver = new Driver("drivertest1", 2001, "male", car);
        driverList.add(driver);
        carList.add(car);
        Race race = new Race("nametest", "locationtest", carList);
        em.persist(race);
        em.getTransaction().commit();
        em.close();
    }

    public static void main(String[] args) {
        populate();
    }
}
