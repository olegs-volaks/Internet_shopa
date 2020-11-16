package application.core.services;

import application.bd.Database;
import application.items.Product;

import java.util.List;
import java.util.function.Predicate;

public class FilterProductsByPriceService {
    private final Database db;

    public FilterProductsByPriceService(Database db) {
        this.db = db;
    }

    public List<Product> execute(Predicate<Product> predicate) {
        return db.filter(predicate);

    }
}

