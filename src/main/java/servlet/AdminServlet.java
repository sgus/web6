package servlet;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getPathInfo() == null) {
            List<User> allUsers = userService.getAllUsers();
            req.setAttribute("users", allUsers);
            req.getRequestDispatcher("/admin.jsp").forward(req, resp);
        } else {
            switch (req.getPathInfo()) {
                case "/delete":
                    userService.deleteById(Long.valueOf(req.getParameter("id")));
                    resp.sendRedirect("/admin");
                    break;
                case "/update":
                    User user = new User(Long.parseLong(req.getParameter("id")), req.getParameter("login"), req.getParameter("email"), req.getParameter("password"), req.getParameter("role"), Long.parseLong(req.getParameter("rating")));
                    userService.updateUser(user);
                    resp.sendRedirect("/admin");
                    break;
                default:
                    break;
            }
        }
    }


}
