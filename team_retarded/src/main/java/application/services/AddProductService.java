package application.services;

import application.bd.Database;

public class AddProductService {

    private final Database db;

    public AddProductService(Database db) {
        this.db = db;
    }
}
