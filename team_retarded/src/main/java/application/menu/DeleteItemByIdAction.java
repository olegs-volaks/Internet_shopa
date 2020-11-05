package application.menu;

import application.controllers.ControllerInterface;

import java.util.Scanner;

public class DeleteItemByIdAction implements MenuAction {

    private ControllerInterface listController;

    public DeleteItemByIdAction(ControllerInterface listController) {
        this.listController = listController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product id: ");
        listController.delete(scanner.nextLong());
    }
}