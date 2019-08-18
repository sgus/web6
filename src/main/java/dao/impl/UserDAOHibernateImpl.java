package dao.impl;

import dao.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserDAOHibernateImpl implements UserDAO {
    @Override
    public List<User> getAllUsers() throws SQLException {
        return null;
    }

    @Override
    public boolean validateUser(String login, String password) throws SQLException {
        return false;
    }

    @Override
    public boolean checkByLogin(String login) throws SQLException {
        return false;
    }

    @Override
    public void addClient(User user) throws SQLException {

    }

    @Override
    public void removeUserByLogin(String login) throws SQLException {

    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void dropTable() throws SQLException {

    }

    @Override
    public User getUserById(long id) throws SQLException {
        return null;
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        return null;
    }

    @Override
    public void removeUserById(Long id) throws SQLException {

    }

    @Override
    public void updateUser(User user) throws SQLException {

    }
}
