package application.ui;


import application.services.DeleteProductService;

import java.util.Scanner;

public class DeleteProductUIAction  implements UIAction {

    private final DeleteProductService service;

    public DeleteProductUIAction(DeleteProductService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter product id to remove:  ");
        long productId = Long.parseLong(scanner.nextLine());
        service.delete(productId);
        System.out.println("Your product was successfully removed.");
    }
}
