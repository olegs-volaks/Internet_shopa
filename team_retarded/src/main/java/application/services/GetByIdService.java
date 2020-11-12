package application.services;

import application.bd.Database;
import application.items.Product;

import java.util.List;
import java.util.Optional;

public class GetByIdService {
    private final Database db;

    public GetByIdService(Database db) {
        this.db = db;
    }

    public Optional<Product> getById(Long id) {
        return db.getById(id);
    }
}
