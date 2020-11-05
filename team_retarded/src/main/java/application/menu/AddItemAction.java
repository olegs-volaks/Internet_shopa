package application.menu;

import application.controllers.ControllerInterface;
import java.util.Scanner;

public class AddItemAction implements MenuAction {

    private ControllerInterface listController;

    public AddItemAction (ControllerInterface listController) {
        this.listController = listController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        System.out.print("Enter specification: ");
        String specification = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        listController.add(productName, specification, price);
    }
}