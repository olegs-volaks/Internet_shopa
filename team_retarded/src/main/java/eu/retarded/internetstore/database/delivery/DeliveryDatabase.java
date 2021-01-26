package eu.retarded.internetstore.database.delivery;

import eu.retarded.internetstore.core.domain.Delivery;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface DeliveryDatabase {

    Long add(Delivery delivery);

    boolean delete(Long id);

    void clear();

    Optional<Delivery> getById(Long id);

    List<Delivery> filter(Predicate<Delivery> predicate);

    List<Delivery> getList();

    boolean isExist(Long id);

    boolean changeTitle(Long id,String title);

    boolean changeRegion(Long id,String region);

    boolean changePrice(Long id,BigDecimal price);

}
