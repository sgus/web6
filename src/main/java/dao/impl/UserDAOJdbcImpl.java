package dao.impl;

import dao.UserDAO;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJdbcImpl implements UserDAO {

    private Connection connection;

    public UserDAOJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery("SELECT * from users");
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6)));
        }
        stmt.close();
        resultSet.close();
        return users;
    }

    public boolean validateUser(String login, String password) throws SQLException {
        try (
                PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users where login=? and  password = ?")
        ) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            ResultSet resultSet1 = pstmt.executeQuery();
            resultSet1.next();
            if (resultSet1.getString(2).equals(password)) {
                return (true);
            }
        }
        return false;
    }

    public boolean checkByLogin(String login) throws SQLException {
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT * FROM users where login=('" + login + "')")) {
            if (resultSet.next()) {
                return (resultSet.getString(2).equals(login));
            }
            return false;
        }
    }


    public void addClient(User user) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("INSERT INTO users(id, login,email, password) VALUES (id,?,?,?)");
        ) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.execute();
        }
    }

    public void removeUserByLogin(String login) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("delete from users where login=('" + login + "')");
        ) {
            preparedStatement.execute();
        }
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, login varchar(255), password varchar(255), rank varchar(255), raiting int(11), primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();
    }

    public User getUserById(long id) throws SQLException {

        try (
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("SELECT * FROM users where id=?");
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            User user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6));


            resultSet.close();
            return user;
        }
    }

    public User getUserByLogin(String login) throws SQLException {
        try (ResultSet resultSet = connection.prepareStatement("SELECT * FROM users where name='" + login + "'").executeQuery()) {
            User user = null;
            while (resultSet.next()) {
                user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6));
            }
            return (user);
        }
    }

    public void removeUserById(Long id) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("DELETE FROM users where id= ?");
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        }
    }

    public void updateUser(User user) throws SQLException {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement
                        ("update users set login = ?,email=?, password=?, `rank`=?, rating=? where id =?");
        ) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRank());
            preparedStatement.setLong(5, (user.getRating()));
            preparedStatement.setLong(6, user.getId());

            preparedStatement.execute();
        }
    }
}
