package dao;

import dao.impl.UserDAOHibernateImpl;
import dao.impl.UserDAOJdbcImpl;
import org.hibernate.cfg.Configuration;
import util.DBHelper;

import java.sql.Connection;
import java.util.Properties;

public abstract class UserDaoFactory {
    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        if(new Properties().getProperty("DAOImplementation").equals("jdbc")) {
            return new UserDAOJdbcImpl();
        } else {
            return new UserDAOHibernateImpl();
        }
    }
}
