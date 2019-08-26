package dao.impl;

import dao.UserDAO;
import model.User;
import org.hibernate.*;
import util.DBHelper;

import java.util.List;

public class UserDAOHibernateImpl implements UserDAO {
    private SessionFactory sessionFactory;
    private Session session;

    private static UserDAO userDAO;

    public UserDAOHibernateImpl() {
        sessionFactory = DBHelper.getSessionFactory();
    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from User");
        return query.list();
    }


    @Override
    public void addUser(User user) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserByLogin(String login) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query2 = session.createQuery("delete from User where login=:login");
        query2.setParameter("login", login).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public User getUserById(long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User userById = (User) session.get(User.class, id);
        transaction.commit();
        session.close();
        return userById;
    }

    @Override
    public User getUserByLogin(String login) {
        session = sessionFactory.openSession();
        Query query = session.createQuery("from User where login=:login");
        query.setParameter("login", login);
        List list = query.list();
        if (list.size()==0){
            session.close();
            return new User();
        }
        User user = (User) list.get(0);
        System.out.println(user);
        session.close();
        return user;

    }

    @Override
    public void removeUserById(Long id) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query2 = session.createQuery("delete from User where id=:id");
        query2.setParameter("id",id).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public boolean validateUser(User user) {
        User userByLogin = getUserByLogin(user.getLogin());
        if (user.getPassword().equals(userByLogin.getPassword())) {
            return true;
        } else {
            return false;
        }
    }


}
