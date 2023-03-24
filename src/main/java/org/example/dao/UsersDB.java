package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDB {
    public static final String DB_LOGIN = "root";
    public static final String DB_PASSWORD = "root";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
    public static Connection connection = null;
    public static Statement statement;


    public static Connection getConnection(){
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  connection;
    }
}
