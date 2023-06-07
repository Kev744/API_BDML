package rest.todo.dao;

import rest.todo.manage.ManageCity;
import rest.todo.model.Movie;
import rest.todo.model.ReleaseWindow;

import java.util.HashMap;
import java.util.Map;


public enum MovieDao {
    instance;

    private Map<String, Movie> contentProvider = new HashMap<>();

    private MovieDao() {
        /*
        Movie m1 = new Movie(Movie.incrementId(), "avatar 2", "180", "en", "christopher nolan", "aliens bleus", "12", ReleaseWindow.newReleaseWindow(), ManageCity.getCities());
        contentProvider.put(m1.getId(), m1);
        Movie m2 = new Movie(Movie.incrementId(), "le diner de con", "100", "fr", "jsp", "thierry lermite", "10", ReleaseWindow.newReleaseWindow(), ManageCity.getCities());
        contentProvider.put(m2.getId(), m2);
        */
    }
    public Map<String, Movie> getModel(){
        return contentProvider;
    }

}