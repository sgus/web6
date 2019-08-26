package dao;

import dao.impl.UserDAOHibernateImpl;

import java.io.IOException;

public interface AbstractDAOFactory {
    public  UserDAO createUserDAOHibernate();
    public  UserDAO createUserDAOJdbc();
    public  UserDAO UserDAO() throws IOException;
}
