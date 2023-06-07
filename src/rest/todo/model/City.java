package rest.todo.model;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="address")
    private String address;

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", days=" + days +
                ", schedules=" + schedules +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @ElementCollection
    @Column(name="day")
    private List<String> days = new ArrayList<>();
    @ElementCollection
    @Column(name="schedule")
    private List<String> schedules = new ArrayList<>();

    public City(){

    }

    public City(String address, List<String> days, List<String> schedules){
        this.address = address;
        this.days = days;
        this.schedules = schedules;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public List<String> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<String> schedules) {
        this.schedules = schedules;
    }
}