package application.ui;

import application.bd.Database;
import application.services.ClearUIActionService;

import java.util.Scanner;

public class ClearUIAction implements UIAction {

    private final ClearUIActionService service;

    public ClearUIAction(ClearUIActionService service) {
        this.service = service;
    }


    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, clear your list : ");
        service.clearProductList();
        System.out.println("Your list is successfully cleared .  ");
    }
}
