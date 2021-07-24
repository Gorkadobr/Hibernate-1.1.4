package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserService u = new UserServiceImpl();
        UserDao dao = new UserDaoHibernateImpl();
        dao.createUsersTable();
        dao.saveUser("Oleg", "Tinkoff", (byte) 50);
        dao.saveUser("Olivia", "B", (byte) 35);
        dao.saveUser("Oliver", "Kl", (byte) 25);
        dao.saveUser("Andy", "Nef", (byte) 27);
        System.out.println(dao.getAllUsers().toString());
        dao.dropUsersTable();
        dao.removeUserById(2);
        dao.cleanUsersTable();
        Util.getSessionFactory().close();

        u.createUsersTable();
        u.saveUser("Irina", "Korobkova", (byte) 24);
        u.saveUser("Lili", "Fig", (byte) 30);
        u.saveUser("Dan", "Kulak", (byte) 12);
        u.saveUser("Gans", "Har", (byte) 18);
        System.out.println(u.getAllUsers().toString());
        u.removeUserById(1);
        u.cleanUsersTable();
        u.dropUsersTable();
    }
}

