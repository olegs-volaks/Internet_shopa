package eu.retarded.internetstore.ui.category;

import eu.retarded.internetstore.core.requests.category.DeleteProductFromCategoryRequest;
import eu.retarded.internetstore.core.responses.category.DeleteProductFromCategoryResponse;
import eu.retarded.internetstore.core.services.category.DeleteProductFromCategoryService;
import eu.retarded.internetstore.ui.UIAction;

import java.util.Scanner;

public class DeleteProductFromCategoryUIAction implements UIAction {
    private final DeleteProductFromCategoryService service;

    public DeleteProductFromCategoryUIAction(DeleteProductFromCategoryService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter category ID: ");
        long categoryId = Long.parseLong(scanner.nextLine());
        System.out.println("Get product by ID: ");
        long productId = Long.parseLong(scanner.nextLine());
        DeleteProductFromCategoryResponse response = service.execute(new DeleteProductFromCategoryRequest(categoryId, productId));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            System.out.println("Product ID " + response.getProductIdId() +
                    " was deleted successfully to category ID " + response.getCategoryId());
        }
    }
}
