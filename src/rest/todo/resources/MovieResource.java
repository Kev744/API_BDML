package rest.todo.resources;

import rest.todo.dao.MovieDao;
import rest.todo.login.LogIn;
import rest.todo.manage.ManageCity;
import rest.todo.model.Movie;
import rest.todo.dao.MovieDao;
import rest.todo.model.Movie;
import rest.todo.model.ReleaseWindow;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.JAXBElement;
import java.sql.Connection;


public class MovieResource {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    String id;
    public MovieResource(UriInfo uriInfo, Request request, String id) {
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    //Application integration
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Movie getMovie() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("yourPersistenceUnitName");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Movie movie = entityManager.find(Movie.class, Integer.parseInt(id));
        return movie;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public Response putMovie(JAXBElement<Movie> Movie) {
        Movie c = Movie.getValue();
        return putAndGetResponse(c);
    }

    @DELETE
    public void deleteMovie() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("yourPersistenceUnitName");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Movie movie = entityManager.find(Movie.class, id);

        if (movie != null) {
            entityManager.remove(movie);

            entityManager.getTransaction().commit();

            entityManager.close();

            entityManagerFactory.close();

        }
    }

    private Response putAndGetResponse(Movie Movie) {
        Response res;
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("yourPersistenceUnitName");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Movie movie = entityManager.find(Movie.class, Movie.getId());
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        if(movie == null) {
            res = Response.noContent().build();
        } else {
            entityManager.detach(movie);
            entityManager.merge(Movie);
            entityManager.getTransaction().commit();
            res = Response.created(uriInfo.getAbsolutePath()).build();
        }

        entityManager.close();

        entityManagerFactory.close();
        return res;
    }
}