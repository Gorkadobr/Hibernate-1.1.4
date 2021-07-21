package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
String url = "jdbc:mysql://localhost:3306/shema";
String userName = "root";
String password = "shema1";
String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
public Connection getConnection() {
    Connection connection = null;
    try {
        Class.forName(DB_DRIVER);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    try {
        connection = DriverManager.getConnection(url,userName,password);
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return connection;
}
}
