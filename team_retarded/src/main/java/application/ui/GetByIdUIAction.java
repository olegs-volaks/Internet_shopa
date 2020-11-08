package application.ui;

import application.bd.Database;
import application.items.Product;

import java.util.Optional;


public class GetByIdUIAction implements UIAction {

    private Database db;

    public GetByIdUIAction(Database db) {
        this.db = db;
    }

    @Override
    public void execute() {
        System.out.println("Get by ID: ");
        db.getById(1L);
    }
}
