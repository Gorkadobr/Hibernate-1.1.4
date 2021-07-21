package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();
    public UserDaoJDBCImpl() {
    }
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(45) NOT NULL," +
                "lastName VARCHAR(45) NOT NULL," +
                "age INT(3) NOT NULL, PRIMARY KEY (id))";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.execute();
        } catch (SQLException throwables) {
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE users";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql) )
        {
            preparedStatement.execute();
        } catch (SQLException throwables) {
            }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sql = "insert into users (name, lastName, age) values (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – "+name +" добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id =?";
       try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
       {
           preparedStatement.setLong(1,id);
           preparedStatement.execute();
       }catch (SQLException throwables) {
       }
       }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        String sql = "select id, name, lastName, age from users";
        Statement statement =null;
        try{
        statement =connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                listUser.add(user);
            }
        }catch (SQLException throwables) {
        }finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return listUser;
    }

    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.execute();
        }catch (SQLException throwables) {
        }
    }
}
