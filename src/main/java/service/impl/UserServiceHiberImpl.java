package service.impl;

import dao.UserDAO;
import dao.UserDaoFactory;
import dao.impl.UserDAOHibernateImpl;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import service.UserService;
import util.DBHelper;

import java.util.List;

public class UserServiceHiberImpl implements UserService {
    private UserDAO userDAO;

    private static UserService userService;
    private SessionFactory sessionFactory;


    private UserServiceHiberImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.userDAO = new UserDaoFactory().UserDAO();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserServiceHiberImpl(DBHelper.getSessionFactory());
        }
        return userService;
    }

    @Override
    public User getUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ((UserDAOHibernateImpl) userDAO).setSession(session);
        User userById = userDAO.getUserById(id);
        transaction.commit();
        session.close();
        return userById;
    }

    @Override
    public User getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ((UserDAOHibernateImpl) userDAO).setSession(session);

        User userByLogin = userDAO.getUserByLogin(login);
        transaction.commit();
        session.close();
        return userByLogin;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ((UserDAOHibernateImpl) userDAO).setSession(session);
        List<User> allUsers = userDAO.getAllUsers();
        transaction.commit();
        session.close();
        return allUsers;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ((UserDAOHibernateImpl) userDAO).setSession(session);
        userDAO.addClient(user);
        transaction.commit();
        session.close();
    }


    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ((UserDAOHibernateImpl) userDAO).setSession(session);
        userDAO.removeUserById(id);
        transaction.commit();
        session.close();

    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ((UserDAOHibernateImpl) userDAO).setSession(session);
        userDAO.updateUser(user);
        transaction.commit();
        session.close();

    }
}
