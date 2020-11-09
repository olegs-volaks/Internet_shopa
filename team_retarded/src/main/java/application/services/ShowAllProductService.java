package application.services;

import application.bd.Database;
import application.items.Product;

import java.util.List;

public class ShowAllProductService {
    private final Database db;

    public ShowAllProductService(Database db) {
        this.db = db;
    }

    public List<Product> showAllProducts() {
        return db.getList();
    }
}
