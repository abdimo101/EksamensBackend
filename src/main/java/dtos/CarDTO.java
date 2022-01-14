package dtos;


import entities.Car;
import entities.Driver;
import entities.Race;

import java.util.ArrayList;
import java.util.List;

public class CarDTO {
    private Integer id;
    private String name;
    private String brand;
    private String make;
    private int year;
    private List<Race> raceList;
    private List<Driver> driverList;


    public CarDTO(String name, String brand, String make, int year) {
        this.name = name;
        this.brand = brand;
        this.make = make;
        this.year = year;
        this.driverList = driverList;
    }

    public CarDTO(Car car) {
        this.id = car.getId();
        this.name = car.getName();
        this.brand = car.getBrand();
        this.make = car.getMake();
        this.year = car.getYear();
    }

    public static List<CarDTO> getCarDTO(List<Car> car){
        List<CarDTO> cDTO = new ArrayList<>();
        car.forEach(ces->cDTO.add(new CarDTO(ces)));
        return cDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Race> getRaceList() {
        return raceList;
    }

    public void setRaceList(List<Race> raceList) {
        this.raceList = raceList;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }
}
