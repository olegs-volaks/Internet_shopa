package eu.retarded.internetstore.database.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@Component
@Transactional
public class OrmDeliveryDatabase implements DeliveryDatabase {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Delivery delivery) {
       return (long) sessionFactory.getCurrentSession().save(delivery);

    }

    @Override
    public boolean delete(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("DELETE Delivery WHERE id =: id");
        query.setParameter("id", id);
        return query.executeUpdate() == 1;
    }

    @Override
    public void clear() {
        sessionFactory.getCurrentSession().createQuery("DELETE  FROM Delivery ").executeUpdate();
    }

    @Override
    public Optional<Delivery> getById(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Delivery.class, id));
    }

    @Override
    public List<Delivery> filter(Predicate<Delivery> predicate) {
       List<Delivery> deliveries = sessionFactory.getCurrentSession()
               .createQuery("SELECT c FROM Delivery c", Delivery.class)
               .getResultList();
        return deliveries.stream().filter(predicate).collect(toList());
    }

    @Override
    public List<Delivery> getList() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT c FROM Delivery c", Delivery.class)
                .getResultList();
    }

    @Override
    public boolean isExist(Long id) {
        return getById(id).isPresent();
    }
}
