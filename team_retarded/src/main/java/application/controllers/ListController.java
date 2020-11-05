package application.controllers;

import application.items.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class ListController implements ControllerInterface {

    private final List<Product> db = new ArrayList<>();
    private long id=1L;

    @Override   //+
    public boolean add(String productName, String specification, double price) {
        db.add(new Product(id, productName, specification, new BigDecimal(Double.toString(price))));
        id++;
        return true;
    }

    @Override   //+
    public boolean delete(long id) {
        return db.removeIf(x -> x.getId() == id);
    }

    @Override
    public boolean delete(Product product) {
        return db.removeIf(x -> x.equals(product));
    }

    @Override
    public boolean delete(Predicate<Product> predicate) {
        return db.removeIf(predicate);
    }

    @Override
    public boolean deleteAll() {
        db.clear();
        return db.isEmpty();
    }

    @Override
    public boolean delete(String productName) {
        return db.removeIf(x -> x.getProductName().equals(productName));
    }

    @Override
    public boolean deleteWithPrice(BigDecimal price) {
        return db.removeIf(x -> x.getPrice().equals(price));
    }

    @Override
    public Optional<Product> findById(long id) {
        return db.stream()
                .filter(t -> t.getId() == id)
                .findAny();
    }

    @Override
    public List<Product> findByProductName(String productName) {
        return db.stream()
                .filter(t -> t.getProductName().equals(productName))
                .collect(toList());
    }

    @Override
    public List<Product> findByPrice(BigDecimal price) {
        return db.stream()
                .filter(t -> t.getPrice().equals(price))
                .collect(toList());
    }

    @Override
    public List<Product> findByPredicate(Predicate<Product> predicate) {
        return db.stream()
                .filter(predicate)
                .collect(toList());
    }

    @Override
    public List<Product> getList() {
        return db;
    }
}