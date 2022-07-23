package web.dao;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.models.Role;
import web.models.User;

import java.util.List;

public interface UserDao extends UserDetailsService {
    List<User> getAllUsers();

    User getUserById(long id);

    long saveUser(User user);

    long addNewUser(User user);

    Role getRoleByName(String role);

    void deleteUser(long id);
}
