package eu.retarded.internetstore.ui.basket;

import eu.retarded.internetstore.core.requests.basket.AddProductToBasketRequest;
import eu.retarded.internetstore.core.responses.basket.AddProductToBasketResponse;
import eu.retarded.internetstore.core.services.basket.AddProductToBasketService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddProductToBasketUIAction implements UIAction {

    @Autowired
    private AddProductToBasketService service;

    @Override
    public void execute() {

        long userID;
        long productID;
        Integer quantity;

        do {
            userID = getUserId();
        } while (userID < 0);

        do {
            productID = getProductId();
        } while (productID < 0);

        do {
            quantity = getQuantity();
        } while (quantity < 0);

        AddProductToBasketResponse response = service.execute(new AddProductToBasketRequest(userID, productID, quantity));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            if (response.productInBasket()) {
                System.out.println("Your product was added in the users basket.");
            } else {
                System.out.println("Your product not added in the users basket.");
            }
        }

    }

    private long getProductId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter product id: ");
        String id = scanner.nextLine();
        id = id.replaceAll("\\s+", "");
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }

    private long getUserId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter user id: ");
        String id = scanner.nextLine();
        id = id.replaceAll("\\s+", "");
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }

    private Integer getQuantity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter product quantity: ");
        String quantity = scanner.nextLine();
        quantity = quantity.replaceAll("\\s+", "");
        try {
            return Integer.parseInt(quantity);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }
}
