package eu.retarded.internetstore.console_ui.category;

import eu.retarded.internetstore.console_ui.UIAction;
import eu.retarded.internetstore.core.requests.category.ShowAllProductsInCategoryRequest;
import eu.retarded.internetstore.core.responses.category.ShowAllProductsInCategoryResponse;
import eu.retarded.internetstore.core.services.category.ShowAllProductsInCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ShowAllProductsInCategoryUIAction implements UIAction {

    @Autowired
    private ShowAllProductsInCategoryService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Show all products in category with ID: ");
        long categoryId = Long.parseLong(scanner.nextLine());
        ShowAllProductsInCategoryResponse response = service.execute(new ShowAllProductsInCategoryRequest(categoryId));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else if(response.getProductList().isEmpty()){
            System.out.println("Zero products belongs to category");
        } else  {
            System.out.println("Products from category " + response.getProductList().get(0).getCategory().getName()+ " : ");
            for (int i=0; i<response.getProductList().size(); i++)
            {
                System.out.println(response.getProductList().get(i));
            }

        }
    }
}
