package eu.retarded.internetstore.database.product;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.domain.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@Transactional
class OrmProductDatabase implements ProductDatabase {

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${product.paging.pageSize}")
    private int pageSize;


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
    public List<Product> getListPaging(int page) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Product b", Product.class)
                .setFirstResult(getFirstResult(page))
                .setMaxResults(getMaxResults(page))
                .getResultList();
    }

    @Override
    public boolean isExist(Long id) {
        return getById(id).isPresent();
    }

    @Override
    public boolean addProductToCategory(Long productId, Long categoryId) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("UPDATE Product SET category.id =: categoryId WHERE id =: id ");
        query.setParameter("categoryId", categoryId);
        query.setParameter("id", productId);
        return query.executeUpdate() == 1;
    }

    @Override
    public boolean removeProductFromCategory(Long productId) {
        Query query = sessionFactory.getCurrentSession().
                createQuery("UPDATE Product SET category.id=null WHERE id =: id ");
        query.setParameter("id", productId);
        return query.executeUpdate() == 1;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void updateProduct(Product product) {
        sessionFactory.getCurrentSession().update(product);
    }


    @Override
    public List<Product> search(String keyWord, String sorting, int page) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Product c WHERE LOWER(c.name) LIKE '%" + keyWord.toLowerCase() + "%'" +
                        " or LOWER(c.description) LIKE '%" + keyWord.toLowerCase() + "%'" +
                        "ORDER BY c.name " + sorting + "", Product.class)
                .setFirstResult(getFirstResult(page))
                .setMaxResults(getMaxResults(page))
                .getResultList();
    }

    @Override
    public List<Product> search(String keyWord, Category category, int page) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Product c WHERE LOWER(c.name) LIKE '%" + keyWord.toLowerCase() + "%'" +
                        " or LOWER(c.description) LIKE '%" + keyWord.toLowerCase() + "%'" +
                        " and c.category LIKE " + category.getName(), Product.class)
                .setFirstResult(getFirstResult(page))
                .setMaxResults(getMaxResults(page))
                .getResultList();
    }

    private int getMaxResults(int page) {
        return page * pageSize;
    }

    private int getFirstResult(int page) {
        return (page * pageSize) - pageSize;
    }


}
