package entities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Table(name = "race")
@Entity
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private String location;
    private Date date;


    @ManyToMany(cascade = CascadeType.PERSIST)
    @Expose
    private List<Car> carList;

    public Race() {
    }

    public Race(String name, String location, List<Car> carList) {
        this.name = name;
        this.location = location;
        this.date = new Date();
        this.carList = carList;
    }
    public Race(String name, String location) {
        this.name = name;
        this.location = location;
        this.date = new Date();
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

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}