package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
