package rest.todo.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public enum LogIn {

    instance("root",System.getenv("MDP_JAVA_API"));

    private String login;
    private String password;


    LogIn(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LogIn getInstance() {
        return instance;
    }

    public Connection toConnect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_tp",getLogin(),getPassword());
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}
