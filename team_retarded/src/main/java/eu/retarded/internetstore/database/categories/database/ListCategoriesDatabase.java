package eu.retarded.internetstore.database.categories.database;

import eu.retarded.internetstore.database.categories.category.ProductListCategory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class ListCategoriesDatabase implements CategoriesDatabase {

    private final List<ProductListCategory> categoriesDatabase = new ArrayList<>();
    private Long id=0L;

    @Override
    public Long addCategory(ProductListCategory productListCategory) {
        id++;
        productListCategory.setId(id);
        categoriesDatabase.add(productListCategory);
        return id;
    }

    @Override
    public boolean removeCategory(String name) {
        categoriesDatabase.removeIf(ListCategory -> ListCategory.getName().equals(name));
        return true;
    }

    @Override
    public boolean removeCategory(Long id) {
        categoriesDatabase.removeIf(ListCategory -> ListCategory.getId() == id);
        return true;
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
    public Optional<ProductListCategory> getCategory(Long id) {
        return categoriesDatabase.stream().filter(listCategory -> listCategory.getId() == id).findFirst();
    }

    @Override
    public boolean isExist(Long id) {
        return getCategory(id).isPresent();
    }
}
