package dao.impl;

import dao.UserDAO;
import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOJdbcImpl implements UserDAO {

    private static UserDAOJdbcImpl ourInstance = new UserDAOJdbcImpl();

    private UserDAOJdbcImpl() {
    }

    public static UserDAOJdbcImpl getInstance() {
        return ourInstance;
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {

            Statement stmt = null;
            stmt = DBHelper.getConnection().createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT * from users");
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6)));
            }
            stmt.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }

    public boolean validateUser(User user) {
        try (
                PreparedStatement pstmt = DBHelper.getConnection().prepareStatement("SELECT * FROM users where login=?")
        ) {
            pstmt.setString(1, user.getLogin().toLowerCase());
            ResultSet resultSet1 = pstmt.executeQuery();
            resultSet1.next();
            return (resultSet1.getString(4).equals(user.getPassword()));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkByLogin(String login) throws SQLException {
        try (Statement stmt = DBHelper.getConnection().createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT * FROM users where login=('" + login + "')")) {
            if (resultSet.next()) {
                return (resultSet.getString(2).equals(login));
            }
            return false;
        }
    }


    public void addUser(User user) {
        try (
                PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement
                        ("INSERT INTO users(id, login,email, password) VALUES (id,?,?,?)");
        ) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserByLogin(String login) {
        try (
                PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement("delete from users where login=('" + login + "')");
        ) {
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() throws SQLException {
        Statement stmt = DBHelper.getConnection().createStatement();
        stmt.execute("create table if not exists users (id bigint auto_increment, login varchar(255), password varchar(255), rank varchar(255), raiting int(11), primary key (id))");
        stmt.close();

    }

    public void dropTable() throws SQLException {
        Statement stmt = DBHelper.getConnection().createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.close();

    }

    public User getUserById(long id) {

        try (
                PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement("SELECT * FROM users where id=?");
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            User user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6));

            resultSet.close();

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public User getUserByLogin(String login) {
        try (PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement("SELECT * FROM users where login=?")) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            User user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getLong(6));
            return (user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeUserById(Long id) {
        try (
                PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement
                        ("DELETE FROM users where id= ?");
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try (
                PreparedStatement preparedStatement = DBHelper.getConnection().prepareStatement
                        ("update users set login = ?, email=?, password=?, role=?, rating=? where id =?");
        ) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setLong(5, user.getRating());
            preparedStatement.setLong(6, user.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
