package application.core.services;

import application.bd.Database;
import application.items.Product;

import java.util.Optional;

public class GetProductByIdService {
    private final Database db;

    public GetProductByIdService(Database db) {
        this.db = db;
    }

    public Optional<Product> getById(Long id) {
        return db.getById(id);
    }
}
