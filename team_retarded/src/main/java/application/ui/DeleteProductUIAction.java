package application.ui;


import application.core.requests.DeleteProductRequest;
import application.core.responses.DeleteProductResponse;
import application.core.services.DeleteProductService;

import java.util.Scanner;

public class DeleteProductUIAction  implements UIAction {

    private final DeleteProductService service;

    public DeleteProductUIAction(DeleteProductService service) {
        this.service = service;
    }

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
            if (response.isProductRemoved()) {
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
