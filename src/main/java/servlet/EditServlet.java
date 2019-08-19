package servlet;

import model.User;
import service.UserService;
import service.impl.UserServiceJdbcImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet
{
    UserService userService = new UserServiceJdbcImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            User user = userService.getUserById(id);
            req.setAttribute("user", user);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/edit.jsp");
            dispatcher.forward(req, resp);

        } catch (NumberFormatException | NullPointerException nfe) {
            resp.sendRedirect("/list");

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(Long.parseLong(req.getParameter("id")), req.getParameter("login"), req.getParameter("email"), req.getParameter("password"), req.getParameter("rank"), Long.parseLong(req.getParameter("rating")));
        userService.updateUser(user);
        resp.sendRedirect("/list");

    }



}
