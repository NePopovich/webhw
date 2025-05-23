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

    private List<String> messages;

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

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void save(User user) {
        currentUser = user;
        userDao.save(user);
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
        currentUser = user;
    }
}
