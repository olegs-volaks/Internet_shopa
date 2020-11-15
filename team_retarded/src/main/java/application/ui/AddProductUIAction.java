package application.ui;
import application.requests.AddProductRequests;
import application.responses.AddProductResponse;
import application.services.AddProductService;
import java.util.Scanner;

public class AddProductUIAction implements UIAction {

    private final AddProductService service;
    private Long id;


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
        AddProductRequests requests = new AddProductRequests(id,name,description,getPrice());
        AddProductResponse response = service.execute(requests);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage()));
        }else {
            System.out.println("New product id was: " + response.getNewProduct().getId());
            System.out.println("Your product was successfully added to list.");
        }
        double price;
        do {
            price = getPrice();
        } while (price < 0);
        service.addProduct(name, description, price);
        System.out.println("Your product was successfully added.");
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

