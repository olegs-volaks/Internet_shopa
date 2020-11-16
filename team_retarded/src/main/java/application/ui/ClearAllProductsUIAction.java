package application.ui;


import application.core.services.ClearAllProductsService;

import java.util.Scanner;

public class ClearAllProductsUIAction implements UIAction {

    private final ClearAllProductsService service;

    public ClearAllProductsUIAction(ClearAllProductsService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure? You want to delete all products?");
        System.out.print("Enter \"Y\" to confirm or any other to cancel: ");
        String result = scanner.nextLine();
        result = result.replaceAll("\\s+", "")
                .toLowerCase();
        if (result.equals("y")) {
            service.execute();
            System.out.println("Your list is successfully cleared ");
        }
    }
}
