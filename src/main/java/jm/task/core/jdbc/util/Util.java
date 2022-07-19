package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getMySQLConnection() throws SQLException {
        String connectionURL = "jdbc:mysql://localhost:3306/test_db";
        return DriverManager.getConnection(connectionURL, "root", "root");
    }
}
