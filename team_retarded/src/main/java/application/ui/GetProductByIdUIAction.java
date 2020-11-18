package application.ui;


import application.core.requests.GetProductByIdRequest;
import application.core.responses.GetProductByIdResponse;
import application.core.services.GetProductByIdService;

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
        System.out.println("Your product was got by ID");
        System.out.println(response.getProduct());
    }
}
