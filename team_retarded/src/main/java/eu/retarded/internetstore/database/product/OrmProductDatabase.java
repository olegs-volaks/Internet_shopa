package eu.retarded.internetstore.database.product;

import eu.retarded.internetstore.core.domain.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
@Transactional
public class OrmProductDatabase implements ProductDatabase {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Long add(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(Predicate<Product> predicate) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Optional<Product> getById(Long id) {
        return Optional.of(sessionFactory.getCurrentSession().get(Product.class, id));
    }

    @Override
    public List<Product> filter(Predicate<Product> predicate) {
        return null;
    }

    @Override
    public List<Product> getList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Product b", Product.class)
                .getResultList();
    }

    @Override
    public boolean isExist(Long id) {
        return false;
    }

    @Override
    public boolean addProductToCategory(Long productId, Long categoryId) {
        return false;
    }

    @Override
    public boolean removeProductFromCategory(Long productId) {
        return false;
    }
}
