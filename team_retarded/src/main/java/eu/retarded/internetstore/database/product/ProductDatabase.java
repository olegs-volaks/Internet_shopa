package eu.retarded.internetstore.database.product;

import eu.retarded.internetstore.core.domain.Category;
import eu.retarded.internetstore.core.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ProductDatabase {

    Long add(Product product);

    boolean delete(Long id);

    void clear();

    Optional<Product> getById(Long id);

    List<Product> filter(Predicate<Product> predicate);

    List<Product> getList();

    List<Product> getListPaging(int page);

    boolean isExist(Long id);

    boolean addProductToCategory(Long productId, Long categoryId);

    boolean removeProductFromCategory(Long productId);

    void updateProduct(Product product);

    List<Product> search(String keyWord, String sorting, int page);

    List<Product> search(String keyWord, Category category, int page);
}
