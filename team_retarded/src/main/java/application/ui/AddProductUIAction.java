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
        System.out.print("Please, enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Please, enter new product description: ");
        String description = scanner.nextLine();
        double price;
        do {
            price = getPrice();
        } while (price < 0);
        service.addProduct(name, description, price);
    }

    private double getPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter new product price: ");
        String price = scanner.nextLine();
        price = price.replaceAll("\\s+", "");
        try {
            return Double.parseDouble(price);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }
}
