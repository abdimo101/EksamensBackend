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
    private Date date;
    private String location;
    private List<CarDTO> carList;

    public RaceDTO(String name, String location, Date date) {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public RaceDTO(Race race) {
        this.id = race.getId();
        this.name = race.getName();
        this.date = race.getDate();
        this.location = race.getLocation();
        this.carList = CarDTO.getCarDTO(race.getCarList());
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<CarDTO> getCarList() {
        return carList;
    }

    public void setCarList(List<CarDTO> carList) {
        this.carList = carList;
    }
}
