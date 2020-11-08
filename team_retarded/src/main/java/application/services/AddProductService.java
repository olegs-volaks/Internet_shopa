package application.services;

import application.bd.Database;

public class AddProductService {

    private final Database db;

    public AddProductService(Database db) {
        this.db = db;
    }

    public void addProduct(String name, String description, double price) {
        db.add(name, description, price);
    }
}
