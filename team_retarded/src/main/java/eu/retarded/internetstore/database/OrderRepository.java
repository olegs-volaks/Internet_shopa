package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findOrderByUser_IdAndStatusIsBetween(Long user_id, int status, int status2, Pageable pageable);

    List<Order> findOrderByUser_IdAndStatusIsBetween(Long user_id, int status, int status2);

    Page<Order> findOrderByUser_IdAndStatusOrStatus(Long user_id, int status, int status2, Pageable pageable);

    List<Order> findOrderByUser_IdAndStatusOrStatus(Long user_id, int status, int status2);


}
