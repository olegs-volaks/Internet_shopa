package application.services;

import application.bd.Database;

public class ShowProductByIDService {

    private final Database db;

    public ShowProductByIDService(Database db) {
        this.db = db;
    }

    public void getById(Long id) {
        db.getById(id);
    }
}
