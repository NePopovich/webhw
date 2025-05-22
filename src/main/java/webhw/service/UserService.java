package webhw.service;

import org.springframework.stereotype.Service;
import webhw.dao.UserDao;
import webhw.model.User;

@Service
public class UserService {

    private UserDao userDao;

    public UserService() {
    }

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        userDao.save(user);
    }
}
