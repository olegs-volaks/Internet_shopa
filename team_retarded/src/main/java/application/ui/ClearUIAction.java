package application.ui;


import application.services.ClearService;

import java.util.Scanner;

public class ClearUIAction implements UIAction {

    private final ClearService service;

    public ClearUIAction(ClearService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure? You want to delete all products?");
        System.out.println("Please press 0 to delete all products");
        int command = scanner.nextInt();
        if (command == 0){
            service.clear();
        }
        System.out.println("Your list is successfully cleared ");
    }
}
