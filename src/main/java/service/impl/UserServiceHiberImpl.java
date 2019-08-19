package service.impl;

import dao.UserDAO;
import dao.impl.UserDAOHibernateImpl;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.UserService;
import util.HiberUtils;

import java.sql.SQLException;
import java.util.List;

public class UserServiceHiberImpl implements UserService {

    private static UserService userService;

    private SessionFactory sessionFactory;

    private UserServiceHiberImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserServiceHiberImpl(HiberUtils.getSessionFactory());
        }
        return userService;
    }
    @Override
    public User getUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
         User userById = new UserDAOHibernateImpl(session).getUserById(id);
        transaction.commit();
        session.close();
        return userById;
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User userByLogin = new UserDAOHibernateImpl(session).getUserByLogin(login);
        transaction.commit();
        session.close();
        return userByLogin;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> allUsers = new UserDAOHibernateImpl(session).getAllUsers();
        transaction.commit();
        session.close();
        return allUsers;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new UserDAOHibernateImpl(session).addClient(user);
        transaction.commit();
        session.close();
    }



    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new UserDAOHibernateImpl(session).removeUserById(id);
        transaction.commit();
        session.close();

    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        new UserDAOHibernateImpl(session).updateUser(user);
        transaction.commit();
        session.close();

    }
}
