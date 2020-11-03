package application.controller;

import application.item.Product;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface ControllerInterface {

    boolean add(String productName, String specification, double price);

    boolean delete(long id);

    //Нужен ли?
    boolean delete(Product product);

    boolean delete(Predicate<Product> predicate);

    boolean deleteAll();

    boolean delete(String productName);

    boolean deleteWithPrice(double price);

    Optional<Product> findById (long id);

    List<Product> findByProductName (String productName);

    List<Product> findByPrice (double price);

    List<Product> findByPredicate(Predicate<Product> predicate);

}
