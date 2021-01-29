package eu.retarded.internetstore.ui.product;


import eu.retarded.internetstore.core.requests.product.GetProductByIdRequest;
import eu.retarded.internetstore.core.responses.product.GetProductByIdResponse;
import eu.retarded.internetstore.core.services.product.GetProductByIdService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GetProductByIdUIAction implements UIAction {

    @Autowired
    private GetProductByIdService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Get product by ID: ");
        long productId = Long.parseLong(scanner.nextLine());
        GetProductByIdResponse response = service.execute(new GetProductByIdRequest(productId));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            System.out.println("Your product was got by ID");
        }
    }
}
