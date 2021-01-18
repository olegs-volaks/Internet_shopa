package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Delivery;


import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface DeliveryDatabase {

    Long add(Delivery delivery);

    boolean delete(Long id);

    boolean delete(Predicate<Delivery> predicate);

    void clear();

    Optional<Delivery> getById(Long id);

    List<Delivery> filter(Predicate<Delivery> predicate);

    List<Delivery> getList();

    boolean isExist(Long id);

}
