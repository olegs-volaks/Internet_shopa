package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    Page<Order> findOrderByUserIdAndStatusEqualsOrStatusEquals(long id, int status1, int status2, Pageable pageable);
    List<Order> findOrderByUserIdAndStatusEqualsOrStatusEquals(long id, int status1, int status2);
    Page<Order> findOrderByUserIdAndStatusBetween(long id, int status1, int status2, Pageable pageable);
    List<Order> findOrderByUserIdAndStatusBetween(long id, int status1, int status2);


}
