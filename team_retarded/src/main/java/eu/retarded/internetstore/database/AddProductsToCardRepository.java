package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddProductsToCardRepository extends JpaRepository<Product, Long> {
}
