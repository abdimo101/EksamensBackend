package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "car")
@Entity
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private String brand;
    private String make;
    private int year;

    @ManyToMany(mappedBy = "carList", cascade = CascadeType.PERSIST)
    private List<Race> raceList;

    @OneToMany(mappedBy = "car", cascade = CascadeType.PERSIST)
    private List<Driver> driverList;

    public Car() {
    }

    public Car(String name, String brand, String make, int year, List<Driver> driverList) {
        this.name = name;
        this.brand = brand;
        this.make = make;
        this.year = year;
        this.driverList = driverList;
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