package eu.retarded.internetstore.database.order;

import eu.retarded.internetstore.core.domain.Order;
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
class OrmOrderDatabase implements OrderDatabase {

    @Autowired
    private SessionFactory sessionFactory;

    private final int productsOnPage=16;

    @Override
    public Long add(Order order) {
        return (Long) sessionFactory.getCurrentSession().save(order);
    }

    @Override
    public boolean delete(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE Order WHERE id =: id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    @Override
    public Optional<Order> getById(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Order.class, id));
    }

    @Override
    public List<Order> getList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Order b", Order.class)
                .getResultList();
    }

    @Override
    public boolean isExist(Long id) { return getById(id).isPresent(); }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void updateOrder(Order order) {
        sessionFactory.getCurrentSession().update(order);
    }

    public int getMaxResults (int page){
        return page*productsOnPage;
    }
    public int getFirstResult (int page){
        return page*productsOnPage-page+1;
    }
}
