package service.impl;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;
import service.UserService;

import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl ourInstance;
   private UserDAO dao = new UserDaoFactory().UserDAO();

    static {
        try {
            ourInstance = new UserServiceImpl();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserServiceImpl getInstance() {
        return ourInstance;
    }

    private UserServiceImpl() throws IOException {
    }


    @Override
    public User getUserById(long id) {
        return dao.getUserById(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return dao.getUserByLogin(login);
    }

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void deleteById(Long id) {
        dao.removeUserById(id);
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public boolean checkUser(User user) {
        return dao.validateUser(user);
    }
}
