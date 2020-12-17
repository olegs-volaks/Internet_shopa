package eu.retarded.internetstore.ui.category;

import eu.retarded.internetstore.core.requests.category.AddCategoryRequest;
import eu.retarded.internetstore.core.responses.category.AddCategoryResponse;
import eu.retarded.internetstore.core.services.category.AddCategoryService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddCategoryUIAction implements UIAction {

    @Autowired
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
