package dao.impl;

import dao.UserDAO;
import model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDAOHibernateImpl implements UserDAO {
    private Session session;

    public UserDAOHibernateImpl(Session session) {
        this.session = session;
    }

    @Override
    public List getAllUsers()  {
        return session.createQuery("from User").list();
    }


    @Override
    public void addClient(User user) {
        session.save(user);
    }

    @Override
    public void removeUserByLogin(String login) {
        Query query2 = session.createQuery("delete from User where login=:login");
        query2.setParameter("login", login).executeUpdate();
    }

    @Override
    public User getUserById(long id) {
        return (User) session.get(User.class, id);

    }

    @Override
    public User getUserByLogin(String login) {
        return null;
    }

    @Override
    public void removeUserById(Long id) {
        session.remove(getUserById(id));
    }

    @Override
    public void updateUser(User user) {
        session.update(user);
    }


}
