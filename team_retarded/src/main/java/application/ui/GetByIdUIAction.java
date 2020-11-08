package application.ui;

import application.bd.Database;
import application.items.Product;

import java.util.Optional;
import java.util.Scanner;


public class GetByIdUIAction implements UIAction {

    private final Database db;

    public GetByIdUIAction(Database db) {
        this.db = db;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Get product by ID: ");
        long productId = Long.parseLong(scanner.nextLine());
        db.getById(productId);
        System.out.println("Your product was got by ID");

    }
}
