package eu.retarded.internetstore.ui.basket;

import eu.retarded.internetstore.core.requests.basket.DeleteProductFromBasketRequest;
import eu.retarded.internetstore.core.responses.basket.DeleteProductFromBasketResponse;
import eu.retarded.internetstore.core.services.basket.DeleteProductFromBasketService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteProductFromBasketUIAction implements UIAction {

    @Autowired
    private DeleteProductFromBasketService service;

    @Override
    public void execute() {

        long userID;
        long productID;


        do {
            userID = getUserId();
        } while (userID < 0);

        do {
            productID = getProductId();
        } while (productID < 0);


        DeleteProductFromBasketResponse response = service.execute(new DeleteProductFromBasketRequest(userID, productID));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            if (response.productNotInBasket()) {
                System.out.println("Your product was removed from the users basket.");
            } else {
                System.out.println("Your product not removed from the users basket.");
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
}