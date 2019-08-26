package servlet;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            User user = userService.getUserById(id);
            System.out.println(user.toString());
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/edit.jsp").forward(req, resp);
        } catch (NumberFormatException | NullPointerException nfe) {
            resp.sendRedirect("/admin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = UserServiceImpl.getInstance();
        User user = new User(Long.parseLong(req.getParameter("id")), req.getParameter("login"), req.getParameter("email"), req.getParameter("password"), req.getParameter("role"), Long.parseLong(req.getParameter("rating")));
        userService.updateUser(user);
        resp.sendRedirect("/admin");

    }


}
