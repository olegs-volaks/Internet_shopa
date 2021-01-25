package eu.retarded.internetstore.ui.delivery;

import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.AddDeliveryResponse;
import eu.retarded.internetstore.core.services.delivery.AddDeliveryService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddDeliveryUIAction implements UIAction {

    @Autowired
    private AddDeliveryService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter delivery");
        String name = scanner.nextLine();
        System.out.println("Please,enter delivery region");
        String region = scanner.nextLine();
        double price;
        do {
            price = getPrice();
        } while (price < 0);
        AddDeliveryResponse response = service.execute(new AddDeliveryRequest(name, region, price));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            System.out.println("Product number " + response.getDeliveryId() + " was added successfully"); // тут product number илм delivery писать?
        }
    }

    private double getPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter delivery price: ");
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
