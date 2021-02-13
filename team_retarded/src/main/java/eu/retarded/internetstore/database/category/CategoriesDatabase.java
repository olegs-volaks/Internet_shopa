package eu.retarded.internetstore.database.category;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.domain.Product;

import java.util.List;
import java.util.Optional;

public interface CategoriesDatabase {

    Long addCategory(Category category);

    boolean removeCategory(String name);

    boolean removeCategory(Long id);

    void clear();

    List<Category> getCategoryList();

    Optional<Category> getCategory(Long id);

    boolean isExist(Long id);

    void updateCategory(Category category);

    List<Product> getProducts(Category category);
}

