package application.database.categories.database;

import application.database.categories.category.ProductListCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ListCategoriesDatabase implements CategoriesDatabase {

    private final List<ProductListCategory> categoriesDatabase = new ArrayList<>();
    private long id;

    @Override
    public long addCategory(ProductListCategory productListCategory) {
        id++;
        productListCategory.setId(id);
        categoriesDatabase.add(productListCategory);
        return id;
    }

    @Override
    public void removeCategory(String name) {
        categoriesDatabase.removeIf(ListCategory -> ListCategory.getName().equals(name));
    }

    @Override
    public void removeCategory(long id) {
        categoriesDatabase.removeIf(ListCategory -> ListCategory.getId() == id);
    }

    @Override
    public void removeCategory(Predicate<ProductListCategory> predicate) {
        categoriesDatabase.removeIf(predicate);
    }

    @Override
    public void clear() {
        categoriesDatabase.clear();
    }

    @Override
    public List<ProductListCategory> getCategoryList() {
        return categoriesDatabase;
    }

    @Override
    public Optional<ProductListCategory> getCategory(long id) {
        return categoriesDatabase.stream().filter(listCategory -> listCategory.getId() == id).findFirst();
    }
}
