package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


 List<Product> findByNameContaining(String name);



}

