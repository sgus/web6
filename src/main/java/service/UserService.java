package service;

import model.User;

import java.util.List;

public interface UserService {

    public User getUserById(long id) ;

    public User getUserByLogin(String login) ;

    public List<User> getAllUsers();

    public void addUser(User user) ;

    public void deleteById(Long id);

    public void updateUser(User user);

    public boolean checkUser(User user);

}
