package application.services;

import application.bd.Database;

public class ShowAllProductService {

    private final Database db;

    public ShowAllProductService(Database db) {
        this.db = db;
    }

    public void showAllProduct(Long id, String name) {
        db.getList();
    }
}
