package application.menu;

import application.controllers.ControllerInterface;

import java.util.Scanner;

public class DeleteItemByPredicateAction implements MenuAction {

    private ControllerInterface listController;

    public DeleteItemByPredicateAction(ControllerInterface listController) {
        this.listController = listController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Define criteria for deletion: ");
        System.out.print("1. All products with the name. 2. All products with the price.");
//        switch (scanner.nextInt()) {
//            case 1 ->
//        };
        listController.delete(scanner.nextLong());
    }
}