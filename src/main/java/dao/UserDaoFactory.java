package dao;

import dao.impl.UserDAOHibernateImpl;
import dao.impl.UserDAOJdbcImpl;
import org.hibernate.cfg.Configuration;
import util.DBHelper;

import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;
import java.util.Properties;

public class UserDaoFactory implements AbstractDAOFactory {


    @Override
    public UserDAO createUserDAOHibernate() {
        return new UserDAOHibernateImpl();
    }

    @Override
    public UserDAO createUserDAOJdbc() {
        return UserDAOJdbcImpl.getInstance();
    }

    @Override
    public UserDAO UserDAO() throws IOException {
        Properties properties = new Properties();
        properties.load(Objects.requireNonNull(UserDaoFactory.class.getClassLoader().getResourceAsStream("config.properties")));
        if(properties.getProperty("DAOImplementation").equals("jdbc")) {
            return createUserDAOJdbc();
        } else {
            return createUserDAOHibernate();
        }
    }
}
