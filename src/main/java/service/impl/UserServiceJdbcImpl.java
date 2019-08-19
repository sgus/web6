package service.impl;

import dao.UserDAO;
import dao.impl.UserDAOJdbcImpl;
import model.User;
import service.UserService;
import util.JDBCUtils;

import java.sql.SQLException;
import java.util.List;

public class UserServiceJdbcImpl implements UserService {

    public User getUserById(long id) {

        return getUserDAO().getUserById(id);
    }

    public User getUserByLogin(String login) {
        return getUserDAO().getUserByLogin(login);
    }

    public List<User> getAllUsers() {
        List<User> allUsers = null;
        allUsers = getUserDAO().getAllUsers();
        return allUsers;
    }

    public void addUser(User user) {
        getUserDAO().addClient(user);

    }



    public void deleteById(Long id) {
        UserDAO userDAO = getUserDAO();
        userDAO.removeUserById(id);
    }

    public void updateUser(User user) {
        UserDAO userDAO = getUserDAO();
        userDAO.updateUser(user);
    }

    private static UserDAO getUserDAO() {
        UserDAO userDAO = new UserDAOJdbcImpl();
       new UserDAOJdbcImpl().setConnection(new JDBCUtils().getConnection());
        return userDAO;
    }
}
