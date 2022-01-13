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
import java.util.Calendar;
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
        Car car = new Car("nametest3", "brandtest3", "maketest3", 2015, driverList);
        Car car1 = new Car("nametest4", "brandtest4", "maketest4", 2005, driverList);
        Driver driver = new Driver("drivertest5", 1975, "male", car);
        Driver driver1 = new Driver("drivertest6", 1981, "female", car);
        driverList.add(driver);
        driverList.add(driver1);
        carList.add(car);
        carList.add(car1);
        Race race = new Race("nametest2","13-01-22", "14:24","locationtest2", carList);
        em.persist(race);
        em.getTransaction().commit();
        em.close();
    }

    public static void main(String[] args) {
        populate();
    }
}
