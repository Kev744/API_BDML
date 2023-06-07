package rest.todo.resources;

import javax.servlet.http.Cookie;

import rest.todo.login.LogIn;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import at.favre.lib.crypto.bcrypt.BCrypt;



@Path("/")
public class ManageLogin {
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String goHome(@Context HttpServletResponse servletResponse) throws IOException {
      servletResponse.sendRedirect("./static/movies.html");
      return null; //TODO replace this stub to something useful
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response logOn(@FormParam("login") String login,
                        @FormParam("passwd") String password,
                        @Context HttpServletResponse servletResponse)  {
        try {
            Connection con = LogIn.instance.toConnect();
            String sql = "SELECT * FROM users WHERE login = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()) { System.out.println("Retry");}
            else {
                boolean verified = BCrypt.verifyer().verify(password.toCharArray(), rs.getString("passwd")).verified;
                if (verified) {
                    Cookie cookie_user = new Cookie("username", login);
                    Cookie cookie_passwd = new Cookie("passwd", rs.getString("passwd"));
                    cookie_user.setPath("./static/create_movie.html");
                    cookie_passwd.setPath("./static/create_movie.html");
                    servletResponse.addCookie(cookie_user);
                    servletResponse.addCookie(cookie_passwd);

                    servletResponse.sendRedirect("./static/create_movie.html");

                } else {
                    String errorMessage = "<h1>Login Failed</h1><p>Invalid username or password.</p>";

                    // Create a Response object with the updated HTML content and media type
                    return Response.ok(errorMessage, MediaType.TEXT_HTML).build();
                }
            }
            con.close();
            }

     catch (Exception e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }

    @POST
    @Path("signup")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void signup(@FormParam("login") String login,
                        @FormParam("passwd") String password,
                        @Context HttpServletResponse servletResponse) throws IOException {
        try {
            Connection con = LogIn.instance.toConnect();
            String sql = "INSERT INTO users(login,passwd) VALUES(?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, BCrypt.withDefaults().hashToString(4, password.toCharArray()));
            int rows = stmt.executeUpdate();
            System.out.println(rows);
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("logout")
    public Response logout(@Context HttpServletResponse servletResponse, @Context HttpServletRequest servletRequest) throws IOException {
        Cookie[] cookies = servletRequest.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue(""); //Reset the cookie
                cookie.setMaxAge(0);
                servletResponse.addCookie(cookie); // Add the updated cookie to the response

            }
        }

        return Response.seeOther(URI.create("./static/movies.html")).build();
    }

}