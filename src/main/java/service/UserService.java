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

public interface UserService  {

    public User getUserById(long id);

    public User getUserByLogin(String login);

    public List<User> getAllUsers();

    public void addUser(User user) ;

    public void cleanUp();

    public void createTable() ;



    public void deleteById(Long id) ;

    public void updateUser(User user) ;

}
