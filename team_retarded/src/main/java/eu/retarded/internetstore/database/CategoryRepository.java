package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
