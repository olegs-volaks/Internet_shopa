package eu.retarded.internetstore.database.cart;

import eu.retarded.internetstore.core.domain.Cart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmCartDatabase implements CartDatabase {

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${cart.paging.pageSize}")
    private int pageSize;

    @Override
    public Long add(Cart cart) {
        return (long) sessionFactory.getCurrentSession().save(cart);
    }

    @Override
    public boolean delete(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE Cart  WHERE id =: id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    @Override
    public Optional<Cart> getById(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Cart.class, id));
    }

    @Override
    public List<Cart> getList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT a FROM Cart  a ", Cart.class)
                .getResultList();
    }

    @Override
    public boolean isExist(Long id) {
        return getById(id).isPresent();
    }

    @Override
    public void clear() {
        sessionFactory.getCurrentSession().createQuery("DELETE from Cart ").executeUpdate();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void updateCart(Cart cart) {
        sessionFactory.getCurrentSession().update(cart);
    }

    @Override
    public List<Cart> getListPaging(int page) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT a FROM Cart a", Cart.class)
                .setFirstResult(getFirstResult(page))
                .setMaxResults(getMaxResults(page))
                .getResultList();
    }

    @Override
    public List<Cart> search(String keyWord, String sorting, int page) { // здесь a  ; / ??
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT a FROM Cart a WHERE LOWER(a.name) LIKE '%" + keyWord.toLowerCase() + "%'" +
                        " or LOWER(a.description) LIKE '%" + keyWord.toLowerCase() + "%'" +
                        "ORDER BY a.name " + sorting + "", Cart.class)
                .setFirstResult(getFirstResult(page))
                .setMaxResults(getMaxResults(page))
                .getResultList();
    }


    private int getFirstResult(int page) {
        return page * pageSize;
    }

    private int getMaxResults(int page) {
        return (page * pageSize) - pageSize;
    }
}
