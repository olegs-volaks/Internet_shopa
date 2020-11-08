package application.ui;

import application.bd.Database;

import java.util.Scanner;

public class DeleteProductUIAction  implements UIAction {

    private  final Database db;

    public DeleteProductUIAction(Database db) {
        this.db = db;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id to remove:  ");
        Long productId = Long.parseLong(scanner.nextLine());
        db.delete(productId);
        System.out.println("Your product was removed from list.");
    }
}
