package webhw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import webhw.dao.UserDao;
import webhw.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private User currentUser;

    private UserDao userDao;

    public UserService() {
    }

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void save(User user) {
        currentUser = user;
        userDao.save(user);
    }

    public User getLastAddUser() {
        List<User> users = new ArrayList<>();
        users = userDao.findAll();
        return users.get(users.size() - 1);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public void getUserByNameAndLastName(String name, String lastName) {
        currentUser = userDao.findByNameAndLastName(name, lastName);
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public void updateUser(User user, Long id) {
        userDao.updateUserById(user, id);
    }
}
