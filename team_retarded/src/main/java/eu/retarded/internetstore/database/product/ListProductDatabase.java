package eu.retarded.internetstore.database.product;

import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.database.category.CategoriesDatabase;
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
    private Long id = 0L;

    @Override
    public Long add(Product product) {
        id++;
        product.setId(id);
        productDatabase.add(product);
        return id;
    }

    @Override
    public boolean delete(Long id) {
        return productDatabase.removeIf(x -> x.getId().equals(id));
    }

    public boolean delete(Predicate<Product> predicate) {
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

    @Override
    public boolean addProductToCategory(Long productId, Long categoryId) {
        return false;
    }

    @Override
    public boolean removeProductFromCategory(Long productId) {
        return false;
    }

}