package rest.todo.client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import rest.*;
import rest.todo.login.LogIn;
import rest.todo.manage.ManageCity;
import rest.todo.model.City;
import rest.todo.model.Movie;
import rest.todo.model.ReleaseWindow;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

public class Tester {




    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("yourPersistenceUnitName");

        // Create an instance of EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Begin a transaction
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Movie movie = new Movie("La Cage dorée", "120","Portuguese","Ruben Alves", "Joaquim Almeida, Rita Blanco","0",new ReleaseWindow("16/01","18/02"), ManageCity.getCities());


        entityManager.merge(movie);

        // Commit the transaction
        transaction.commit();


    }
}
