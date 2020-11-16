package application.core.services;

import application.bd.Database;

public class DeleteProductService {
    private final Database db;

    public DeleteProductService(Database db) {
        this.db = db;
    }

    public void execute(Long id) {
        db.delete(id);
    }
}
