package entities;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "driver")
@Entity
public class Driver implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private int birthYear;
    private String gender;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Car car;


    public Driver() {
    }

    public Driver(String name, int birthYear, String gender, Car car) {
        this.name = name;
        this.birthYear = birthYear;
        this.gender = gender;
        this.car = car;
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