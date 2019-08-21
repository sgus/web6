package service.impl;

import dao.UserDAO;
import dao.UserDaoFactory;
import dao.impl.UserDAOJdbcImpl;
import model.User;
import service.UserService;
import util.DBHelper;

import java.util.List;

public class UserServiceJdbcImpl implements UserService {

    private UserDAO userDAO;
    private static UserService userService;
    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserServiceJdbcImpl();
        }
        return userService;
    }

    private UserServiceJdbcImpl() {
        this.userDAO = new UserDaoFactory().UserDAO();
    }

    public User getUserById(long id) {

        return getUserDAO().getUserById(id);
    }

    public User getUserByLogin(String login) {
        return getUserDAO().getUserByLogin(login);
    }

    public List<User> getAllUsers() {
        List<User> allUsers = userDAO.getAllUsers();
        return allUsers;
    }

    public void addUser(User user) {
        getUserDAO().addClient(user);

    }

    public void deleteById(Long id) {
        userDAO.removeUserById(id);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    private UserDAO getUserDAO() {
        return userDAO;
    }
}
