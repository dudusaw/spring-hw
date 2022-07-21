package hiber.dao;

import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);
   User getByCar(String model, int serial);
   List<User> listUsers();
}
