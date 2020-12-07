package application.ui.category;

import application.core.requests.category.AddProductToCategoryRequest;
import application.core.responses.category.AddProductToCategoryResponse;
import application.core.services.category.AddProductToCategoryService;
import application.ui.UIAction;

import java.util.Scanner;

public class AddProductToCategoryUIAction implements UIAction {

    private final AddProductToCategoryService service;

    public AddProductToCategoryUIAction(AddProductToCategoryService service) {
        this.service = service;
    }

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
                    " was added successfully to category ID "+ response.getCategoryId());
        }
    }
}



