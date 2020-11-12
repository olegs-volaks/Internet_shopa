package application.services;

import application.bd.Database;
import application.items.Product;
import java.util.Optional;

public class ShowProductByIDService {

    private final Database db;

    public ShowProductByIDService(Database db) {
        this.db = db;
    }

    public Optional<Product> getById(Long id) {
        return db.getById(id);
    }
}
