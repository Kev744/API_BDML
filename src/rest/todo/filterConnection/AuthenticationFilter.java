package rest.todo.filterConnection;

import rest.todo.login.LogIn;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthenticationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie[] cookie = request.getCookies();

        try {
            Connection con = LogIn.instance.toConnect();
            String sql = "SELECT * FROM users WHERE login = ? AND passwd = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cookie[0].getValue());
            stmt.setString(2, cookie[1].getValue());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                filterChain.doFilter(request, response);
            }
            }
        catch (Exception ignored){

            response.sendRedirect("./movies.html");

        }
    }

    @Override
    public void destroy() {

    }
}
