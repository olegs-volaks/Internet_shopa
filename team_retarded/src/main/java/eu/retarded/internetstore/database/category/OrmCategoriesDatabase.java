package eu.retarded.internetstore.database.category;

import eu.retarded.internetstore.core.domain.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Component
public class OrmCategoriesDatabase implements CategoriesDatabase {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long addCategory(Category category) {
        return (Long) sessionFactory.getCurrentSession().save(category);
    }

    @Override
    public boolean removeCategory(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE Category WHERE name =: name");
        query.setParameter("name", name);
        return query.executeUpdate() == 1;
    }

    @Override
    public boolean removeCategory(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE Category WHERE id =: id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    @Override
    public void clear() {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Category").executeUpdate();
    }

    @Override
    public List<Category> getCategoryList() {
        return sessionFactory
                .getCurrentSession()
                .createQuery("SELECT c FROM Category c", Category.class)
                .getResultList();
    }

    @Override
    public Optional<Category> getCategory(Long id) {

        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Category.class, id));
    }

    @Override
    public boolean isExist(Long id) {
        return getCategory(id).isPresent();
    }
}
