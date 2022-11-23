package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findUserById(long id) {
        return userDao.findUserById(id);
    }

    @Override
    public void deleteUserById(long id) {
        userDao.deleteUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void createOrUpdateUser(User user) {
        if (user.getId() == null) {
            createUser(user);
        } else {
            updateUser(user);
        }
    }

    private void createUser(User user) {
        userDao.createUser(user);
    }

    private void updateUser(User user) {
        User userFromDB = userDao.findUserById(user.getId());
        if (user.getFirstName().equals("")) {
            user.setFirstName(userFromDB.getFirstName());
        }
        if (user.getLastName().equals("")) {
            user.setLastName(userFromDB.getLastName());
        }
        user.setMarried(user.isMarried());
        userDao.updateUser(user);
    }
}
