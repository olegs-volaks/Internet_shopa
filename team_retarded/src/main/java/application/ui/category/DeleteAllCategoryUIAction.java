package application.ui.category;

import application.core.requests.category.DeleteAllCategoryRequest;
import application.core.services.category.DeleteAllCategoryService;
import application.ui.UIAction;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.Scanner;

@DIComponent
public class DeleteAllCategoryUIAction implements UIAction {

    @DIDependency
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
