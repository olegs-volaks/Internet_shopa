package application.ui;

import application.bd.Database;
import application.items.Product;
import application.services.GetByIdService;

import java.util.Optional;
import java.util.Scanner;


public class GetByIdUIAction implements UIAction {

    private final GetByIdService service;

    public GetByIdUIAction(GetByIdService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Get product by ID: ");
        long productId = Long.parseLong(scanner.nextLine());
        service.getById(productId);
        System.out.println("Your product was got by ID");

    }
}
