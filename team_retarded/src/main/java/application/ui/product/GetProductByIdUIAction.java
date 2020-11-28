package application.ui.product;


import application.core.requests.product.GetProductByIdRequest;
import application.core.responses.product.GetProductByIdResponse;
import application.core.services.product.GetProductByIdService;
import application.ui.UIAction;

import java.util.Scanner;


public class GetProductByIdUIAction implements UIAction {

    private final GetProductByIdService service;

    public GetProductByIdUIAction(GetProductByIdService service) {
        this.service = service;
    }

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
            System.out.println(response.getProduct());
        }
    }
}
