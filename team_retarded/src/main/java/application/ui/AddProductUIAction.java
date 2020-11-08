package application.ui;

import application.services.AddProductService;

import java.util.Scanner;

public class AddProductUIAction implements UIAction {

    private final AddProductService service;

    public AddProductUIAction(AddProductService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter new product name:");
        String name = scanner.nextLine();
        System.out.print("Please, enter new product name:");
        String description = scanner.nextLine();
        System.out.print("Please, enter new product name:");
        double price = scanner.nextDouble();
        service.addProduct(name, description, price);
    }
}
