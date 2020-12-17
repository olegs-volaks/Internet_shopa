package eu.retarded.internetstore.ui.product;


import eu.retarded.internetstore.core.requests.product.DeleteProductRequest;
import eu.retarded.internetstore.core.responses.product.DeleteProductResponse;
import eu.retarded.internetstore.core.services.product.DeleteProductService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteProductUIAction implements UIAction {

    @Autowired
    private DeleteProductService service;

    @Override
    public void execute() {
        long id;
        do {
            id = getId();
        } while (id < 0);
        DeleteProductResponse response = service.execute(new DeleteProductRequest(id));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            if (response.isProductDeleted()) {
                System.out.println("Your product was removed from list.");
            } else {
                System.out.println("Your product not removed from list.");
            }
        }

    }

    private long getId() {
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

}
