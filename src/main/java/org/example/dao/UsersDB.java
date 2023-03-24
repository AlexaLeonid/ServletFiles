package org.example.dao;

import java.sql.*;

public class UsersDB {
    public static final String DB_LOGIN = "root";
    public static final String DB_PASSWORD = "root";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/usersdb";
    public static Connection connection = null;
    public static Statement statement;


    public static Connection getConnection(){
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return  connection;
    }
}
