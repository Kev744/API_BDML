package rest.todo.model;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@Entity
@Table(name="movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="duration")
    private String duration;
    @Column(name="lang")
    private String language;
    @Column(name="directors")
    private String director;
    @Column(name="actors")
    private String actors;
    @Column(name="age")
    private String age;
    @Embedded
    private ReleaseWindow releaseDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private List<City> cities;

    public Movie(){

    }

    public Movie(String title, String duration, String language, String director, String actors, String age, ReleaseWindow releaseWindow, List<City> cities){
        this.title = title;
        this.duration = duration;
        this.language = language;
        this.director = director;
        this.actors = actors;
        this.age = age;
        this.releaseDate = releaseWindow;
        this.cities = cities;
    }



    public int getId(){
        return id;
    }

    public void setId(int id) {this.id = id;}
    public String getTitle() {
        return title;
    }

    public Movie setTitle(String title) {
        this.title = title;
        return null;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public ReleaseWindow getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(ReleaseWindow releaseWindow) {
        this.releaseDate = releaseWindow;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}