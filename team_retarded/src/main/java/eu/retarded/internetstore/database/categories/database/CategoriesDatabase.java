package eu.retarded.internetstore.database.categories.database;

import eu.retarded.internetstore.database.categories.category.ProductListCategory;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface CategoriesDatabase {

    long addCategory(ProductListCategory ListCategory);

    boolean removeCategory(String name);

    boolean removeCategory(long id);

    void removeCategory(Predicate<ProductListCategory> predicate);

    void clear();

    List<ProductListCategory> getCategoryList();

    Optional<ProductListCategory> getCategory(long id);

    boolean isExist(long id);
}
