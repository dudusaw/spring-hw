package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection connection = Util.getMySQLConnection();
        UserDao userDao = new UserDaoJDBCImpl(connection);
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
