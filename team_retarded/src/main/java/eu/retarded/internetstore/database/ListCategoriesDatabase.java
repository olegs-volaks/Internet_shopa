package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

//@Component
public class ListCategoriesDatabase implements CategoriesDatabase {

    private final List<ProductCategory> categoriesDatabase = new ArrayList<>();
    private Long id = 0L;

    @Override
    public Long addCategory(ProductCategory productCategory) {
        id++;
        productCategory.setId(id);
        categoriesDatabase.add(productCategory);
        return id;
    }

    @Override
    public boolean removeCategory(String name) {
        return categoriesDatabase.removeIf(ListCategory -> ListCategory.getName().equals(name));
    }

    @Override
    public boolean removeCategory(Long id) {
        return categoriesDatabase.removeIf(ListCategory -> ListCategory.getId().equals(id));
    }

    @Override
    public void removeCategory(Predicate<ProductCategory> predicate) {
        categoriesDatabase.removeIf(predicate);
    }

    @Override
    public void clear() {
        categoriesDatabase.clear();
    }

    @Override
    public List<ProductCategory> getCategoryList() {
        return categoriesDatabase;
    }

    @Override
    public Optional<ProductCategory> getCategory(Long id) {
        return categoriesDatabase.stream().filter(listCategory -> listCategory.getId().equals(id)).findFirst();
    }

    @Override
    public boolean isExist(Long id) {
        return getCategory(id).isPresent();
    }
}
