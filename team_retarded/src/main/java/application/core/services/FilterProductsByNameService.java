package application.core.services;

import application.bd.Database;
import application.items.Product;

import java.util.List;
import java.util.function.Predicate;

public class FilterProductsByNameService {
    private final Database db;

    public FilterProductsByNameService(Database db) {
        this.db = db;
    }

    public List<Product> Filter(Predicate<Product> predicate) {
        return db.filter(predicate);

    }
}

