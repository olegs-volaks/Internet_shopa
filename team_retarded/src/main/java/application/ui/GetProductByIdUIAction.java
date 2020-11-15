package application.ui;


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
        if (service.getById(productId).isEmpty()) {
            System.out.println("Your product not exist");
        } else {
            System.out.println("Your product was got by ID");
            System.out.println(service.getById(productId).get());
        }
    }
}
