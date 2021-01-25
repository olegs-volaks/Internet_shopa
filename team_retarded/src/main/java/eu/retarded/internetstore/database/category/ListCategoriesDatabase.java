package eu.retarded.internetstore.database.category;

import eu.retarded.internetstore.core.domain.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Component
public class ListCategoriesDatabase implements CategoriesDatabase {

    private final List<Category> categoriesDatabase = new ArrayList<>();
    private Long id = 0L;

    @Override
    public Long addCategory(Category category) {
        id++;
        category.setId(id);
        categoriesDatabase.add(category);
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
    public void clear() {
        categoriesDatabase.clear();
    }

    @Override
    public List<Category> getCategoryList() {
        return categoriesDatabase;
    }

    @Override
    public Optional<Category> getCategory(Long id) {
        return categoriesDatabase.stream().filter(listCategory -> listCategory.getId().equals(id)).findFirst();
    }

    @Override
    public boolean isExist(Long id) {
        return getCategory(id).isPresent();
    }
}
