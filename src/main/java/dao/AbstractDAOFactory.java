package dao;

import dao.impl.UserDAOHibernateImpl;

public interface AbstractDAOFactory {
    public  UserDAO createUserDAOHibernate();
    public  UserDAO createUserDAOJdbc();
    public  UserDAO UserDAO();
}
