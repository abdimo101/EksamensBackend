package dtos;

import entities.Car;
import entities.Race;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RaceDTO {
    private Integer id;
    private String name;
    private String date;
    private String time;
    private String location;
    private List<Car> carList;

    public RaceDTO(String name, String date, String time, String location, List<Car> carList) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.carList = carList;
    }

    public RaceDTO(Race race) {
        this.id = race.getId();
        this.name = race.getName();
        this.date = race.getDate();
        this.time = race.getTime();
        this.location = race.getLocation();
        this.carList = race.getCarList();
    }

    public static List<RaceDTO> getDTOs(List<Race> races){
        List<RaceDTO> rDTO = new ArrayList<>();
        races.forEach(res->rDTO.add(new RaceDTO(res)));
        return rDTO;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
