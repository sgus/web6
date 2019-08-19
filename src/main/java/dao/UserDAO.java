package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface UserDAO {

    List<User> getAllUsers() throws SQLException;

    boolean validateUser(String login, String password) throws SQLException;

    boolean checkByLogin(String login) throws SQLException;

    void addClient(User user) throws SQLException;

    void removeUserByLogin(String login) throws SQLException;

    void createTable() throws SQLException;

    void dropTable() throws SQLException;

    User getUserById(long id) throws SQLException;

    User getUserByLogin(String login) throws SQLException;

    void removeUserById(Long id) throws SQLException;

    void updateUser(User user) throws SQLException;

    void setConnection(Connection connection);
}
