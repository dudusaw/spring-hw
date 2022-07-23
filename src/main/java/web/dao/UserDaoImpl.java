package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import web.models.Role;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> users = entityManager.createQuery("from User", User.class);
        return users.getResultList();
    }

    @Override
    public User getUserById(long id) {
        TypedQuery<User> query = entityManager.createQuery("from User u where u.id=:id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public long saveUser(User user) {
        user = entityManager.merge(user);
        return user.getId();
    }

    @Override
    public long addNewUser(User user) {
        entityManager.persist(user);
        return user.getId();
    }

    @Override
    public Role getRoleByName(String role) {
        TypedQuery<Role> query = entityManager.createQuery("from Role r where r.role=:role", Role.class);
        query.setParameter("role", role);
        return query.getSingleResult();
    }

    @Override
    public void deleteUser(long id) {
        Query query = entityManager.createQuery("delete from User u where u.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        TypedQuery<User> query = entityManager.createQuery("from User u where u.email=:email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
}
