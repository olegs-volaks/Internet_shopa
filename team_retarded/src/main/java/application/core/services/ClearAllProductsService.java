package application.core.services;

import application.database.ProductDatabase;

public class ClearAllProductsService {
    private final ProductDatabase db;

    public ClearAllProductsService(ProductDatabase db) {
        this.db = db;
    }

    public void execute() {
        db.clear();
    }
}
