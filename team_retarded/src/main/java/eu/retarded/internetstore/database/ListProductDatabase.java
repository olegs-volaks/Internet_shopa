package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.database.categories.category.ProductListCategory;
import eu.retarded.internetstore.database.categories.database.CategoriesDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

@Component
public class ListProductDatabase implements ProductDatabase {

    @Autowired
    private CategoriesDatabase categoriesDatabase;
    private final List<Product> productDatabase = new ArrayList<>();
    private long id;

    @Override
    public long add(Product product) {
        id++;
        product.setId(id);
        productDatabase.add(product);
        return id;
    }

    @Override
    public boolean delete(long id) {
        deleteFromCategories(product -> product.getId() == id);
        productDatabase.removeIf(x -> x.getId() == id);
        return true;
    }

    @Override
    public boolean delete(Predicate<Product> predicate) {
        deleteFromCategories(predicate);
        productDatabase.removeIf(predicate);
        return true;
    }

    @Override
    public void clear() {
        categoriesDatabase.clear();
        productDatabase.clear();
    }

    @Override
    public Optional<Product> getById(long id) {
        return productDatabase.stream()
                .filter(t -> t.getId() == id)
                .findAny();
    }

    @Override
    public List<Product> filter(Predicate<Product> predicate) {
        return productDatabase.stream()
                .filter(predicate)
                .collect(toList());
    }

    @Override
    public List<Product> getList() {
        return productDatabase;
    }

    @Override
    public boolean isExist(long id) {
        return getById(id).isPresent();
    }

    private void deleteFromCategories(Predicate<Product> predicate) {
        List<ProductListCategory> categories = categoriesDatabase.getCategoryList();
        for (ProductListCategory category : categories) {
            category.remove(predicate);
        }
    }
}