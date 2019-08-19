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
import java.util.List;

@WebServlet("/list/*")
public class ListServlet extends HttpServlet {
    UserService userService = new UserServiceJdbcImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() == null) {
            List<User> allUsers = userService.getAllUsers();
            req.setAttribute("users", allUsers);
            req.getRequestDispatcher("list.jsp").forward(req, resp);
        } else {
            switch (req.getPathInfo()) {
                case "/delete":
                    userService.deleteById(Long.valueOf(req.getParameter("id")));
                    resp.sendRedirect("/list");
                    break;


                case "/update":
                    User user = new User(Long.parseLong(req.getParameter("id")), req.getParameter("login"), req.getParameter("email"), req.getParameter("password"), req.getParameter("rank"), Long.parseLong(req.getParameter("rating")));
                    userService.updateUser(user);
                    resp.sendRedirect("/list");

                    break;
                default:
                    break;
            }
        }
    }


}
