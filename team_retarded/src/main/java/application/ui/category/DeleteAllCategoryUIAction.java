package application.ui.category;

import application.core.services.category.DeleteAllCategoryService;
import application.ui.UIAction;

import java.util.Scanner;

public class DeleteAllCategoryUIAction implements UIAction {

    private final DeleteAllCategoryService service;

    public DeleteAllCategoryUIAction(DeleteAllCategoryService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure? you want to delete all categories?");
        System.out.println("Enter \"Y\" to confirm or any other to cancel: ");
        String result = scanner.nextLine();
        result = result.replaceAll("\\s+", "")
                .toLowerCase();
        if (result.equals("y")) {
            service.execute();
            System.out.println("Your list of categories successfully deleted ");
        }
    }


}