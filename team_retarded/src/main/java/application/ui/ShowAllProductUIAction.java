package application.ui;

import application.bd.Database;

import java.util.Scanner;

public class ShowAllProductUIAction  implements UIAction {

    private Database db;

    public ShowAllProductUIAction(Database db) {
        this.db = db;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter ID product: ");
        long productId = Long.parseLong(scanner.nextLine());
        db.getById(productId);
        System.out.println("Please, enter product name: ");
        String name = scanner.nextLine();
        db.getList();
        System.out.println("All products are successfully found  ");

    }
}
