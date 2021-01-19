package eu.retarded.internetstore.database;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

//@Component
public class ListProductDatabase implements ProductDatabase {

    @Autowired
    private CategoriesDatabase categoriesDatabase;
    private final List<Product> productDatabase = new ArrayList<>();
    private Long id =0L;

    @Override
    public Long add(Product product) {
        id++;
        product.setId(id);
        productDatabase.add(product);
        return id;
    }

    @Override
    public boolean delete(Long id) {
        deleteFromCategories(product -> product.getId().equals(id));
        return productDatabase.removeIf(x -> x.getId().equals(id));
    }

    @Override
    public boolean delete(Predicate<Product> predicate) {
        deleteFromCategories(predicate);
        return productDatabase.removeIf(predicate);
    }

    @Override
    public void clear() {
        categoriesDatabase.clear();
        productDatabase.clear();
    }

    @Override
    public Optional<Product> getById(Long id) {
        return productDatabase.stream()
                .filter(t -> t.getId().equals(id))
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
    public boolean isExist(Long id) {
        return getById(id).isPresent();
    }


    private void deleteFromCategories(Predicate<Product> predicate) {
        List<ProductCategory> categories = categoriesDatabase.getCategoryList();
        for (ProductCategory category : categories) {
            category.remove(predicate);
        }
    }
}