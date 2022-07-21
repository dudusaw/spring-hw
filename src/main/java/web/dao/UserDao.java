package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void saveUser(User user);

    void deleteUser(long id);
}
