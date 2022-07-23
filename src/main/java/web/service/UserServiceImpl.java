package web.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.models.Role;
import web.models.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public long saveUser(User user) {
        processNewUser(user);
        return userDao.saveUser(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    @Override
    public long addNewUser(User user) {
        processNewUser(user);
        return userDao.addNewUser(user);
    }

    private void processNewUser(User user) {
        List<Role> roles = user.getRoles();
        for (int i = 0; i < roles.size(); i++) {
            roles.set(i, userDao.getRoleByName(roles.get(i).getRole()));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
