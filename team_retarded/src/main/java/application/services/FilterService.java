package application.services;

import application.bd.Database;
import application.items.Product;

import java.util.List;
import java.util.function.Predicate;

public class FilterService {
    private final Database db;

    public FilterService(Database db) {
        this.db = db;
    }

    public List<Product> Filter(Predicate<Product> predicate) {
        return db.filter(predicate);

    }
}

