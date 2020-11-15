package application.core.services;

import application.bd.Database;
import application.items.Product;

import java.util.List;

public class ShowAllProductsService {
    private final Database db;

    public ShowAllProductsService(Database db) {
        this.db = db;
    }

    public List<Product> showAllProducts() {
        return db.getList();
    }
}
