package eu.retarded.internetstore.database.cart;

import eu.retarded.internetstore.core.domain.Cart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class OrmCartDatabase implements  CartDatabase {

    @Autowired private SessionFactory sessionFactory;

    @Override
    public Long add(Cart cart) {
        return (long) sessionFactory.getCurrentSession().save(cart);
    }

    @Override
    public boolean delete(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE Cart WHERE id =: id");
        query.setParameter("id",id);
        return query.executeUpdate() == 1;
    }

    @Override
    public Optional<Cart> getById(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Cart.class,id));
    }

    @Override
    public List<Cart> getList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM c",Cart.class)
                .getResultList();
    }
    @Override
    public boolean isExist(Long id) {
        return getById(id).isPresent();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void updateCart(Cart cart) {
        sessionFactory.getCurrentSession().update(cart);
    }
}
