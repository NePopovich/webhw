package webhw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import webhw.controllers.StartController;
import webhw.dao.UserDao;
import webhw.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private UserDao userDao;

    public UserService() {
    }

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        StartController.setCurrentUser(user);
        userDao.save(user);
    }


    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    public void getUserByNameAndLastName(String name, String lastName) {
        StartController.setCurrentUser((User) userDao.findByNameAndLastName(name, lastName).orElse(null));
    }

    public void deleteUser(User user) {
        userDao.delete(user);
    }

    public void updateUser(User user, Long id) {
        userDao.updateUserById(user, id);
        StartController.setCurrentUser(user);
    }
}
