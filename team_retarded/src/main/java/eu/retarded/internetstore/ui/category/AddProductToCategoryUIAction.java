package eu.retarded.internetstore.ui.category;

import eu.retarded.internetstore.core.requests.category.AddProductToCategoryRequest;
import eu.retarded.internetstore.core.responses.category.AddProductToCategoryResponse;
import eu.retarded.internetstore.core.services.category.AddProductToCategoryService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddProductToCategoryUIAction implements UIAction {

    @Autowired
    private AddProductToCategoryService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter category ID: ");
        long categoryId = Long.parseLong(scanner.nextLine());
        System.out.println("Get product by ID: ");
        long productId = Long.parseLong(scanner.nextLine());
        AddProductToCategoryResponse response = service.execute(new AddProductToCategoryRequest(categoryId, productId));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            System.out.println("Product ID " + response.getProductIdId() +
                    " was added successfully to category ID " + response.getCategoryId());
        }
    }
}



