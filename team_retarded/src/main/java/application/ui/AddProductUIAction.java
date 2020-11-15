package application.ui;

import application.core.requests.AddProductRequest;
import application.core.responses.AddProductResponse;
import application.core.services.AddProductService;

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
        AddProductResponse response = service.execute(new AddProductRequest(name, description, price));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            System.out.println("Product was added successfully");
        }
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
