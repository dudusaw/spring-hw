package hiber.service;

import hiber.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    User getByCar(String model, int serial);
    List<User> listUsers();
}
