package com.thisastergroup.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Basic SQL connection class to connect to the database

public class SQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/main_schema";
    private static final String USER = "root";
    private static final String PASS = "daniel1123";
    private Connection conn = null;

    /**
     * Connects to the DB using the URL, USER and PASS
     * catchs any SQL exception and prints the error message
     * 
     * @return Connection an objectt with the DB
     * @see Connection
     * 
     */
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.err.println("Connection to database failed: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
