package servlet;

import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("login"), req.getParameter("password"));
        if (!userService.checkUser(user)){
            req.setAttribute("check",false);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
            return;
        }
        User userByLogin = userService.getUserByLogin(user.getLogin());
        if (userByLogin.getRole().equals("user")) {

            req.getRequestDispatcher("user.jsp").forward(req, resp);
        } else if (userByLogin.getRole().equals("admin")){
            resp.sendRedirect("/admin");
        } else {
            req.setAttribute("check",true);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }
}
