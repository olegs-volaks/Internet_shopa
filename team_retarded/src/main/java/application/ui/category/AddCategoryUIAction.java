package application.ui.category;

import application.core.requests.category.AddCategoryRequest;
import application.core.responses.category.AddCategoryResponse;
import application.core.services.category.AddCategoryService;
import application.ui.UIAction;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.Scanner;

@DIComponent
public class AddCategoryUIAction implements UIAction {

    @DIDependency
    private AddCategoryService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter new category name: ");
        String name = scanner.nextLine();
        AddCategoryResponse response = service.execute(new AddCategoryRequest(name));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            System.out.println("Category Nr " + response.getCategoryId() + " was added successfully");
        }
    }
}
