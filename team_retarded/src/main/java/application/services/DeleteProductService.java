package application.services;

import application.bd.Database;

public class DeleteProductService {
    private final Database db;

    public DeleteProductService(Database db) {
        this.db = db;
    }

    public void delete(Long id) {
        db.delete(id);
    }
}
