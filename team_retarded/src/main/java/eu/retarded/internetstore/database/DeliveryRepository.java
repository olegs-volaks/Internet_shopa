package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
