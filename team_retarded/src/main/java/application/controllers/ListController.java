package application.controllers;

import application.items.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ListController implements ControllerInterface {

    private final List<Product> db = new ArrayList<>();
    private long id;

    @Override
    public boolean add(String productName, String specification, double price) {
        db.add(new Product(id, productName, specification, price));
        id++;
        return true;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }

    @Override
    public boolean delete(Predicate<Product> predicate) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public boolean delete(String productName) {
        return false;
    }

    @Override
    public boolean deleteWithPrice(double price) {
        return false;
    }

    @Override
    public Optional<Product> findById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findByProductName(String productName) {
        return null;
    }

    @Override
    public List<Product> findByPrice(double price) {
        return null;
    }

    @Override
    public List<Product> findByPredicate(Predicate<Product> predicate) {
        return null;
    }
}
