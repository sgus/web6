package dao;

import model.User;

import java.sql.*;
import java.util.List;

public interface UserDAO {

    List getAllUsers() ;

    void addClient(User user) ;

    void removeUserByLogin(String login) ;

    User getUserById(long id) ;

    User getUserByLogin(String login) ;

    void removeUserById(Long id) ;

    void updateUser(User user)  ;

}
