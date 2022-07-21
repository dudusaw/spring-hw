package web.dao;

import org.springframework.stereotype.Component;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> users = entityManager.createQuery("from User", User.class);
        List<User> resultList = users.getResultList();
        return resultList;
    }

    @Override
    public void saveUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(long id) {
        Query query = entityManager.createQuery("delete from User u where u.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
