package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        createConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS `user` " +
                        "(id BIGINT AUTO_INCREMENT PRIMARY KEY, `name` VARCHAR(255), lastname VARCHAR(255), age TINYINT);");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void dropUsersTable() {
        createConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DROP TABLE IF EXISTS `user`;");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void saveUser(String name, String lastName, byte age) {
        createConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO `user`(`name`, lastname, age) values (?, ?, ?);")) {
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setByte(3, age);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void removeUserById(long id) {
        createConnection(connection -> {
            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM `user` us WHERE us.id = ?")) {
                statement.setLong(1, id);
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        createConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM `user`");
                while (resultSet.next()) {
                    String name = resultSet.getString(2);
                    String lastName = resultSet.getString(3);
                    byte age = resultSet.getByte(4);
                    User user = new User(name, lastName, age);
                    users.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        return users;
    }

    public void cleanUsersTable() {
        createConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("TRUNCATE TABLE `user`");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void createConnection(Consumer<Connection> func) {
        try (Connection connection = Util.getMySQLConnection()) {
            func.accept(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
