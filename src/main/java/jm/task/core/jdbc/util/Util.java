package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static Connection connection;
    public static Connection getMySQLConnection() {
        try {
            if (connection != null) return connection;
            String connectionURL = "jdbc:mysql://localhost:3306/test_db";
            Connection connection = DriverManager.getConnection(connectionURL, "root", "root");
            Util.connection = connection;
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
