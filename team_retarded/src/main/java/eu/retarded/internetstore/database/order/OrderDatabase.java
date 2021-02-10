package eu.retarded.internetstore.database.order;

import eu.retarded.internetstore.core.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDatabase {

    Long add(Order order);

    boolean delete(Long id);

    Optional<Order> getById(Long id);

    List<Order> getList();
    List<Order> getListWithPaging(int page);

    boolean isExist(Long id);

    void updateOrder(Order order);

}
