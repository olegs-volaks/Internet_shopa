package application.database;

import application.items.Product;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ProductDatabase {

    long add(Product product);

    boolean delete(long id);

    boolean delete(Predicate<Product> predicate);

    void clear();

    Optional<Product> getById(long id);

    List<Product> filter(Predicate<Product> predicate);

    List<Product> getList();

    boolean isExist(long id);

}
