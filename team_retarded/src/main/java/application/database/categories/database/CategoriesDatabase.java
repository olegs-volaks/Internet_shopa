package application.database.categories.database;

import application.database.categories.category.ProductListCategory;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface CategoriesDatabase {

    long addCategory(ProductListCategory ListCategory);

    void removeCategory(String name);

    void removeCategory(long id);

    void removeCategory(Predicate<ProductListCategory> predicate);

    void clear();

    List<ProductListCategory> getCategoryList();

    Optional<ProductListCategory> getCategory(long id);
}
