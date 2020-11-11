package application.ui;

import application.services.ShowAllProductService;

import java.util.Scanner;

public class ShowAllProductUIAction implements UIAction {

    private final ShowAllProductService service;

    public ShowAllProductUIAction(ShowAllProductService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter ID product: ");
        long productId= Long.parseLong(scanner.nextLine());
        service.showAllProduct();
        System.out.println("All products are successfully found:  ");


    }
}
