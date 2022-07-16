package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        execute(session -> {
            NativeQuery<?> nativeQuery = session.createNativeQuery("CREATE TABLE IF NOT EXISTS `user` " +
                    "(id BIGINT AUTO_INCREMENT PRIMARY KEY, `name` VARCHAR(255), lastname VARCHAR(255), age TINYINT);");
            nativeQuery.executeUpdate();
        });
    }

    @Override
    public void dropUsersTable() {
        execute(session -> {
            NativeQuery<?> nativeQuery = session.createNativeQuery("DROP TABLE IF EXISTS `user`;");
            nativeQuery.executeUpdate();
        });
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        execute(session -> {
            session.save(new User(name, lastName, age));
        });
    }

    @Override
    public void removeUserById(long id) {
        execute(session -> {
            User user = session.load(User.class, id);
            session.delete(user);
        });
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        execute(session -> {
            users.addAll(session.createQuery("from User", User.class).list());
        });
        return users;
    }

    @Override
    public void cleanUsersTable() {
        execute(session -> {
            NativeQuery<?> nativeQuery = session.createNativeQuery("DELETE FROM `user`");
            nativeQuery.executeUpdate();
        });
    }

    private void execute(Consumer<Session> task) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            task.accept(session);

            transaction.commit();
        }
    }
}
