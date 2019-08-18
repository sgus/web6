package servlet;

import exception.DBException;
import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/list/*")
public class ListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            UserService userService = new UserService();
            if (req.getPathInfo() == null) {
                List<User> allUsers = new UserService().getAllUsers();
                req.setAttribute("users", allUsers);
                req.getRequestDispatcher("list.jsp").forward(req, resp);
            } else {
                switch (req.getPathInfo()) {
                    case "/delete":
                        userService.deleteById(Long.valueOf(req.getParameter("id")));
                        resp.sendRedirect("/list");
                        break;
                    case "/edit":
                        Long id = Long.valueOf(req.getParameter("id"));
                        User user = userService.getUserById(id);
                        System.out.println(user);
                        req.setAttribute("user", user);
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/edit.jsp");
                        dispatcher.forward(req, resp);
                        break;

                    case "/update":
                         user = new User(Long.parseLong(req.getParameter("id")), req.getParameter("login"), req.getParameter("email"), req.getParameter("password"),req.getParameter("rank"),Long.parseLong(req.getParameter("rating")));

                        boolean updateUser = userService.updateUser(user);
                        if (updateUser) {
                            resp.sendRedirect("/list");
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (SQLException | DBException ex) {
            throw new ServletException(ex);
        }
    }


}
