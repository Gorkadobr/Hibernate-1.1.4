package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    Session session = Util.getSessionFactory().openSession();
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction tx = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(45) NOT NULL," +
                "lastName VARCHAR(45) NOT NULL," +
                "age INT(3) NOT NULL, PRIMARY KEY (id))").executeUpdate();
        tx.commit();
    }


    @Override
    public void dropUsersTable() {
        Transaction tx = session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS users";
        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        tx.commit();
    }
    @Override
    public void saveUser(String name, String lastname, byte age) {
        Transaction tx = session.beginTransaction();
        User user = new User(name, lastname, age);
        user.setId(null);
        session.save(user);
        tx.commit();
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        Transaction tx = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        tx.commit();
    }

    @Override
    public List<User> getAllUsers() {
        Transaction tx = session.beginTransaction();
        List<User> users = session.createQuery("FROM User ").list();
        tx.commit();
        return users;

    }

    @Override
    public void cleanUsersTable() {
        Transaction tx = session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        tx.commit();
    }
}


