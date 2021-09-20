package jm.task.core.jdbc.util;

import java.sql.*;


public class Util {

    static private String connectionURL = "jdbc:mysql://localhost:3306/mysql?autoReconnect=true&useSSL=false";
    static private String password = "rfrfzrhfcjnf93";
    static private String userName = "root";


    static public Connection getConnection() {
        Connection connection = null;
        try {
            // реализуйте настройку соеденения с БД

            connection = DriverManager.getConnection(connectionURL, userName, password);
            if (connection.isClosed()) {
                System.out.println("соединение нет");
            }
        } catch (SQLException e) {
            System.out.println("Что пошло не так, соединения нет и  есть исключение (");
            e.printStackTrace();

        }
        return connection;
    }
}


















