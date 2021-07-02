package io.muic.ssc.assn.a4.webapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This class can be used to initialize the database connection
public class DatabaseConnection {
    public static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException
    {
        // Initialize all the information regarding
        // Database Connection
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:5555/";
        // Database name to access
        String dbName = "login";
        String dbUsername = "pk";
        String dbPassword = "suhavni";

        Class.forName(dbDriver);
        return DriverManager.getConnection(dbURL + dbName,
                dbUsername,
                dbPassword);
    }
}
