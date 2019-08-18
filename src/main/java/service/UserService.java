package service;

import dao.UserDAO;
import dao.impl.UserDAOJdbcImpl;
import exception.DBException;
import model.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {


    public User getUserById(long id) throws DBException {
        try {
            return getUserDAO().getUserById(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public User getUserByLogin(String login) throws SQLException {
        return getUserDAO().getUserByLogin(login);
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> allUsers = getUserDAO().getAllUsers();
        return allUsers;
    }

    public boolean addUser(User user) throws DBException, SQLException {
        if (getUserDAO().checkByLogin(user.getLogin())) {
            return false;
        }
        getUserDAO().addClient(user);
        return true;
    }


    public void cleanUp() throws DBException {
        UserDAO dao = getUserDAO();
        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void createTable() throws DBException {
        UserDAO dao = getUserDAO();
        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private static Connection getMysqlConnection() {
        try {

            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("web6_db?").          //db name
                    append("user=root&").          //login
                    append("password=1234").       //password
                    append("&useTimezone=true&serverTimezone=UTC&useSSL=false");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserDAO getUserDAO() {
        return new UserDAOJdbcImpl(getMysqlConnection());
    }

    public boolean deleteById(Long id) throws SQLException {
        UserDAO userDAO = getUserDAO();
        userDAO.removeUserById(id);
        return true;
    }

    public boolean updateUser(User user) throws SQLException {
        UserDAO userDAO = getUserDAO();
        userDAO.updateUser(user);
        return true;
    }
}
