package application.ui;

import application.bd.Database;
import application.items.Product;
import application.services.GetByIDService;

import java.util.Optional;
import java.util.Scanner;


public class GetByIdUIAction implements UIAction {

    private final GetByIDService service;

    public GetByIdUIAction(GetByIDService service) {
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
