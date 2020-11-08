package application.ui;

import application.bd.Database;
import application.items.Product;

public class FilterUIAction implements UIAction {

    private Database db;

    public FilterUIAction(Database db) {
        this.db = db;
    }

    @Override
    public void execute() {
        System.out.println("Make filter of all products: ");
       // db.filter().
    }
}
