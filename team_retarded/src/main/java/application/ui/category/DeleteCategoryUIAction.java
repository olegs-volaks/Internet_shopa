package application.ui.category;

import application.core.requests.category.DeleteCategoryRequest;
import application.core.responses.category.DeleteCategoryResponse;
import application.core.services.category.DeleteCategoryService;
import application.ui.UIAction;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.Scanner;

@DIComponent
public class DeleteCategoryUIAction implements UIAction {

    @DIDependency
    private DeleteCategoryService service;

    @Override
    public void execute() {
        long id;
        do {
            id = getId();
        } while (id < 0);
        DeleteCategoryResponse response = service.execute(new DeleteCategoryRequest(id));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            if (response.isDeleted()) {
                System.out.println("Your category has been successfully deleted");
            } else {
                System.out.println("Your category has not been deleted!");
            }
        }
    }

    private long getId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter category id: ");
        String id = scanner.nextLine();
        id = id.replaceAll("\\s+", "");
        try {
            return Long.parseLong(id);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }
}
