package com.example.one_hundred_and_twenty_fourth.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionManager {
    private Connection connection;

    public DataBaseConnectionManager(String username, String password, String dbUrl, String driver
    ) throws SQLException, ClassNotFoundException {
        Class.forName(driver);
        this.connection = DriverManager.getConnection(dbUrl, username, password);
    }
    public Connection getConnection() {
        return this.connection;
    }
}
