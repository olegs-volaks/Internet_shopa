package eu.retarded.internetstore.database.category;

import eu.retarded.internetstore.core.domain.ProductCategory;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface CategoriesDatabase {

    Long addCategory(ProductCategory ListCategory);

    boolean removeCategory(String name);

    boolean removeCategory(Long id);

    void removeCategory(Predicate<ProductCategory> predicate);

    void clear();

    List<ProductCategory> getCategoryList();

    Optional<ProductCategory> getCategory(Long id);

    boolean isExist(Long id);
}

