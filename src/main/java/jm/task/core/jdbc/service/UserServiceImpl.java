package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao d = new UserDaoJDBCImpl();

    public void createUsersTable() throws SQLException {
        d.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        d.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        d.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        d.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        return d.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        d.cleanUsersTable();
    }
}
