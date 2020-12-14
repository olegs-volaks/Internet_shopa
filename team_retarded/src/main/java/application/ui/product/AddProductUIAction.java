package application.ui.product;

import application.core.requests.product.AddProductRequest;
import application.core.responses.product.AddProductResponse;
import application.core.services.product.AddProductService;
import application.ui.UIAction;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.Scanner;

@DIComponent
public class AddProductUIAction implements UIAction {

    @DIDependency
    private AddProductService service;

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
            System.out.println("Product number " + response.getProductId() + " was added successfully");
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
