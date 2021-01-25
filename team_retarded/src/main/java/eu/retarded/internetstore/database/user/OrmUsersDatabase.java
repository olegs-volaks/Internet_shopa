package eu.retarded.internetstore.database.user;

import eu.retarded.internetstore.core.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmUsersDatabase implements UsersDatabase {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(User user) {
        return (Long) sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public boolean delete(Long id) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("DELETE User WHERE id =: id")
                .setParameter("id", id)
                .executeUpdate()==1;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(User.class, id));
    }

    @Override
    public List<User> getList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM User b", User.class)
                .getResultList();
    }

    @Override
    public void clear() {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM User").executeUpdate();
    }

    @Override
    public boolean isExist(Long id) {
        return getUserById(id).isPresent();
    }
}
