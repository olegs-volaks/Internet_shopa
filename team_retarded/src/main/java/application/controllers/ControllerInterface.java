package application.controllers;

import application.items.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ControllerInterface {

    boolean add(String productName, String specification, double price);

    boolean delete(long id);

    //For deleting?
    boolean delete(Product product);

    boolean delete(Predicate<Product> predicate);

    boolean deleteAll();

    boolean delete(String productName);

    boolean deleteWithPrice(BigDecimal price);

    Optional<Product> findById(long id);

    List<Product> findByProductName(String productName);

    List<Product> findByPrice(BigDecimal price);

    List<Product> findByPredicate(Predicate<Product> predicate);

    List<Product> getList();

}
