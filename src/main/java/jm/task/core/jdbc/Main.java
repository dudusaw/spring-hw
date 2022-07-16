package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();
        UserService userService = new UserServiceImpl(userDao);
        userService.createUsersTable();
        for (int i = 1; i < 5; i++) {
            String name = "asd" + i;
            userService.saveUser(name, "ads", (byte) 4);
            System.out.printf("User с именем – %s добавлен в базу данных%n", name);
        }
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
