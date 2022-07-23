package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    User getUserById(long id);

    List<User> getAllUsers();

    long saveUser(User user);

    void deleteUser(long id);

    long addNewUser(User user);
}
