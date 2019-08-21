package dao;

import dao.impl.UserDAOHibernateImpl;
import dao.impl.UserDAOJdbcImpl;
import org.hibernate.cfg.Configuration;
import util.DBHelper;

import java.sql.Connection;
import java.util.Properties;

public class UserDaoFactory implements AbstractDAOFactory {


    @Override
    public UserDAO createUserDAOHibernate() {
        return new UserDAOHibernateImpl();
    }

    @Override
    public UserDAO createUserDAOJdbc() {
        return new UserDAOJdbcImpl();
    }

    @Override
    public UserDAO UserDAO() {
        if(new Properties().getProperty("DAOImplementation").equals("jdbc")) {
            return createUserDAOJdbc();
        } else {
            return createUserDAOHibernate();
        }
    }
}
