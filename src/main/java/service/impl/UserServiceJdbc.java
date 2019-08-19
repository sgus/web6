package service.impl;

import dao.UserDAO;
import dao.impl.UserDAOJdbcImpl;
import model.User;
import service.UserService;
import util.MySQLConnUtils;

import java.sql.SQLException;
import java.util.List;

public class UserServiceJdbc implements UserService {

    public User getUserById(long id) {

        try {
            return getUserDAO().getUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByLogin(String login) {
        try {
           return getUserDAO().getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> allUsers = null;
        try {
            allUsers = getUserDAO().getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void addUser(User user) {
        try {
            if (getUserDAO().checkByLogin(user.getLogin())) {
            }
        getUserDAO().addClient(user);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void cleanUp() {
        UserDAO dao = getUserDAO();
        try {
            dao.dropTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        UserDAO dao = getUserDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteById(Long id) {
        UserDAO userDAO = getUserDAO();
        try {
            userDAO.removeUserById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        UserDAO userDAO = getUserDAO();
        try {
            userDAO.updateUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static UserDAO getUserDAO() {
        UserDAO userDAO = new UserDAOJdbcImpl();
        userDAO.setConnection(new MySQLConnUtils().getConnection());
        return userDAO;
    }
}
