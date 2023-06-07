package rest.todo.resources;

import rest.todo.model.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;



@Path("/movies")
public class MyMovies {

    // for the browser
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get/{id}")
    public Movie getMovieHTMLById(@PathParam("id") String id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("yourPersistenceUnitName");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Movie movie = entityManager.find(Movie.class, Integer.parseInt(id));
        return movie;
    }
    /*
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/post/{title}/{duration}/{language}/{director}/{actors}/{age}")
    public Movie postMovie(
            @PathParam("title") String titre,
            @PathParam("duration") String duration,
            @PathParam("language") String language,
            @PathParam("director") String director,
            @PathParam("actors") String actors,
            @PathParam("age") String age) {
        Movie movie = new Movie(Movie.incrementId(), titre, duration, language, director, actors, age, ReleaseWindow.newReleaseWindow(), ManageCity.getCities());
        MovieDao.instance.getModel().put(movie.getId(), movie);
        return movie;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/put/{id}/{title}")
    public Movie putMovie(@PathParam("id") String id, @PathParam("title") String title) {
        return MovieDao.instance.getModel().get(id).setTitle(title);
    }
    */
    @DELETE
    @Path("/delete/{id}")
    public String deleteMovie(@PathParam("id") String id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("yourPersistenceUnitName");

        // Create an instance of EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Movie movie = entityManager.find(Movie.class, id); // Replace "id" with the actual ID of the entity

        if (movie != null) {
            entityManager.remove(movie);

            entityManager.getTransaction().commit();

            entityManager.close();

            entityManagerFactory.close();

            return "Delete successfully";

        } else {
            entityManager.close();

            entityManagerFactory.close();
            return "Delete: Movie with " + id + " not found";
        }


    }
}
