package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

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
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
            Properties properties = new Properties();
            properties.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            properties.setProperty(Environment.HBM2DDL_AUTO,"update");
            properties.setProperty(Environment.DRIVER,"com.mysql.cj.jdbc.Driver" );
            properties.setProperty(Environment.USER, "root");
            properties.setProperty(Environment.PASS, "shema1");
            properties.setProperty(Environment.URL, "jdbc:mysql://localhost:3306/shema");
            Configuration cfg = new Configuration();
            cfg.setProperties(properties);
            cfg.addAnnotatedClass(User.class);
            SessionFactory factory = cfg.buildSessionFactory();
        return factory;
    }
}
