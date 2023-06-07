package rest.todo.resources;


import org.hibernate.Hibernate;
import rest.todo.dao.MovieDao;
import rest.todo.manage.ManageCity;
import rest.todo.model.City;
import rest.todo.model.Movie;
import rest.todo.model.ReleaseWindow;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;

/// Will map the resource to the URL Movies
@Path("/movies")
public class MoviesResource {

    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public static List<Movie> getMovies()  {


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("yourPersistenceUnitName");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String movieQueryStr = "SELECT DISTINCT m FROM Movie m";
        TypedQuery<Movie> movieQuery = entityManager.createQuery(movieQueryStr, Movie.class);
        List<Movie> movies = movieQuery.getResultList();

        for (Movie movie : movies) {
            for (City city : movie.getCities()) {
                // Initialize the collections within an active Hibernate session
                Hibernate.initialize(city.getDays());
                Hibernate.initialize(city.getSchedules());
            }
        }

        entityManager.close();

        entityManagerFactory.close();


        return movies;
    }


    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public long getCount() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("yourPersistenceUnitName");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String countMovieQuery = "SELECT DISTINCT count(m) FROM Movie m";
        Query countMovie = entityManager.createQuery(countMovieQuery);
        long count = (long) countMovie.getSingleResult();

        entityManager.close();

        entityManagerFactory.close();

        return count;
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newMovie(
            @FormParam("title") String title,
            @FormParam("duration") String duration,
            @FormParam("language") String language,
            @FormParam("director") String director,
            @FormParam("actors") String actors,
            @FormParam("age") String age,
            @Context HttpServletResponse servletResponse) throws IOException {
        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("yourPersistenceUnitName");

            EntityManager entityManager = entityManagerFactory.createEntityManager();

            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();

            Movie movie = new Movie(title, duration,language,director, actors,age,ReleaseWindow.newReleaseWindow(), ManageCity.getCities());

            entityManager.merge(movie);

            transaction.commit();

            entityManager.close();

            entityManagerFactory.close();


        }
        catch (Exception e) {e.printStackTrace();}
        /*Movie Movie = new Movie(titre, summary);
        if (description != null) {
            Movie.setDescription(description);
        }*/
        //MovieDao.instance.getModel().put(movie.getId(), movie);

        //servletResponse.sendRedirect("../create_movie.html");
    }

    // Defines that the next path parameter after Movies is
    // treated as a parameter and passed to the MovieResources
    // Allows to type http://localhost:8080/rest.Movie/rest/Movies/1
    // 1 will be treaded as parameter Movie and passed to MovieResource
    @Path("{movie}")
    public MovieResource getMovie(@PathParam("movie") String id) {
        return new MovieResource(uriInfo, request, id);
    }

}