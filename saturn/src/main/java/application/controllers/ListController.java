package application.controllers;

import application.items.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class ListController implements application.controllers.ControllerInterface {

    private final List<Product> db = new ArrayList<>();
    private long id;

    @Override
    public boolean add(String productName, String specification, BigDecimal price) {
        db.add(new Product(id, productName, specification, price));
        id++;
        return true;
    }

    @Override
    public boolean delete(long id) {
        return db.removeIf(x -> x.getId() == id);
    }

    @Override
    public boolean delete(Product product) {
        return db.removeIf(x -> x.equals(product));
    }

    @Override
    public boolean delete(Predicate<Product> predicate) {
        return db.removeIf(t -> t.equals(predicate));
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public boolean delete(String productName) {
        return db.removeIf(x -> x.getProductName().equals(productName));
    }

    @Override
    public boolean deleteWithPrice(BigDecimal price) {
        return db.removeIf(x -> x.getPrice() == price);
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
                .filter(t -> t.equals(predicate))
                .collect(toList());
    }
}
