package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductRepository extends JpaRepository<Product, Long> {

 Page<Product> findByNameContaining(String keyWord, Pageable pageable);
 List<Product> findByNameContaining(String keyWord);

  Page<Product> findAllByCategory_Id (long categoryId, Pageable pageable);
  List<Product> findAllByCategory_Id (long categoryId);
}

