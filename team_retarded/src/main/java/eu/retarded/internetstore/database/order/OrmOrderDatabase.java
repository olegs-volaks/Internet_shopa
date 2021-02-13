package eu.retarded.internetstore.database.order;

import eu.retarded.internetstore.core.domain.Order;
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
class OrmOrderDatabase implements OrderDatabase {

    @Autowired
    private SessionFactory sessionFactory;

    @Value("${product.paging.pageSize}")
    private int pageSize;

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
    public List<Order> getListWithPaging(int page) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT b FROM Order b", Order.class)
                .setFirstResult(getFirstResult(page))
                .setMaxResults(getMaxResults(page))
                .getResultList();
    }

    @Override
    public boolean isExist(Long id) {
        return getById(id).isPresent();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void updateOrder(Order order) {
        sessionFactory.getCurrentSession().update(order);
    }

    public int getMaxResults(int page) {
        return page * pageSize;
    }

    public int getFirstResult(int page) {
        return page * pageSize - pageSize;
    }

    @Override
    public void clear() {
        sessionFactory.getCurrentSession().createQuery("DELETE from Cart ").executeUpdate();
    }

}
