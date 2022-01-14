package dtos;

import entities.Car;

import entities.Driver;


import java.util.ArrayList;
import java.util.List;

public class DriverDTO {
    private int id;
    private String name;
    private int birthYear;
    private String gender;
    private Car car;

    public DriverDTO(String name, int birthYear, String gender, Car car) {
        this.name = name;
        this.birthYear = birthYear;
        this.gender = gender;
        this.car = car;
    }

    public DriverDTO(Driver de) {
        this.id = de.getId();
        this.name = de.getName();
        this.birthYear = de.getBirthYear();
        this.gender = de.getGender();
        this.car = de.getCar();
    }

    public static List<DriverDTO> getDtos(List<Driver> des){
        List<DriverDTO> dDTO = new ArrayList();
        des.forEach(de->dDTO.add(new DriverDTO(de)));
        return dDTO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
