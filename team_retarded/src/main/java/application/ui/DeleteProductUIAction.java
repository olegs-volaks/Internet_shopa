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
        System.out.println("Please, enter product id to remove:  ");
        long productId = Long.parseLong(scanner.nextLine());
        db.delete(productId);
        System.out.println("Your product was successfully removed.");

    }
}
