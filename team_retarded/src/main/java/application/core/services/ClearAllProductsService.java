package application.core.services;

import application.database.Database;

public class ClearAllProductsService {
    private final Database db;

    public ClearAllProductsService(Database db) {
        this.db = db;
    }

    public void execute() {
        db.clear();
    }
}
