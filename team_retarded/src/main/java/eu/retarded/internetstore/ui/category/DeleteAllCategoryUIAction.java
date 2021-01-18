package eu.retarded.internetstore.ui.category;

import eu.retarded.internetstore.core.requests.category.DeleteAllCategoryRequest;
import eu.retarded.internetstore.core.services.category.DeleteAllCategoryService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DeleteAllCategoryUIAction implements UIAction {

    @Autowired
    private DeleteAllCategoryService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure? you want to delete all categories?");
        System.out.println("Enter \"Y\" to confirm or any other to cancel: ");
        String result = scanner.nextLine();
        result = result.replaceAll("\\s+", "")
                .toLowerCase();
        if (result.equals("y")) {
            service.execute(new DeleteAllCategoryRequest());
            System.out.println("Your list of categories successfully deleted ");
        }
    }


}
