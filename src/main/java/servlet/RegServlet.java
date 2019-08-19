package servlet;

import model.User;
import service.UserService;
import service.impl.UserServiceJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/reg")
public class RegServlet extends HttpServlet {
    UserService userService = new UserServiceJdbcImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("login"), req.getParameter("email"), req.getParameter("password"));

        userService.addUser(user);
        resp.sendRedirect("register.jsp");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("register.jsp");

    }
}


