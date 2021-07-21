package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl u = new UserServiceImpl();
        u.createUsersTable();
        u.saveUser("Irina", "Korobkova", (byte) 24);
        u.saveUser("Lili", "Fig", (byte) 30);
        u.saveUser("Dan","Kulak", (byte) 12);
        u.saveUser("Gans","Har",(byte) 18);
        System.out.println(u.getAllUsers().toString());
        u.removeUserById(1);
        u.cleanUsersTable();
    }
}
