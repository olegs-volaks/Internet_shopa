package eu.retarded.internetstore.database.product;

import eu.retarded.internetstore.core.domain.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@Transactional
public class OrmProductDatabase implements ProductDatabase {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Product product) {
        return (Long) sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public boolean delete(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE Product WHERE id =: id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    //public boolean delete(Predicate<Product> predicate) { return false; }

    @Override
    public void clear() {
        sessionFactory.getCurrentSession().createQuery("DELETE from Product ").executeUpdate();
    }

    @Override
    public Optional<Product> getById(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Product.class, id));
    }

    @Override
    public List<Product> filter(Predicate<Product> predicate) {
         return sessionFactory.getCurrentSession().createQuery("SELECT b FROM Product b", Product.class)
                 .stream().filter(predicate)
                 .collect(Collectors.toList());
    }

    @Override
    public List<Product> getList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Product b", Product.class)
                .getResultList();
    }

    @Override
    public boolean isExist(Long id) { return getById(id).isPresent(); }

    @Override
    public boolean addProductToCategory(Long productId, Long categoryId) {
        Query query =sessionFactory.getCurrentSession().
                createQuery("UPDATE Product SET categoryId =: categoryId WHERE id =: id ");
        query.setParameter("categoryId", categoryId);
        query.setParameter("id", productId);
        return query.executeUpdate() == 1;
    }

    @Override
    public boolean removeProductFromCategory(Long productId) {
        Query query=sessionFactory.getCurrentSession().
                createQuery("UPDATE Product SET categoryId=null WHERE id =: id ");
        query.setParameter("id", productId);
        return query.executeUpdate() == 1;
    }
}
