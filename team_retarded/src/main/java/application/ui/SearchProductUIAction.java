package application.ui;

import application.core.requests.SearchProductRequest;
import application.core.responses.SearchProductResponse;
import application.core.services.SearchProductService;

import java.util.Scanner;

public class SearchProductUIAction implements UIAction {

    private final SearchProductService service;

    public SearchProductUIAction(SearchProductService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter a product name : ");
        String name = scanner.nextLine();
        System.out.println("Please, enter a product description: ");
        String description = scanner.nextLine();
        SearchProductResponse response = service.execute(new SearchProductRequest(name,description));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field -  "
            + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            System.out.println("Product " + response.getProducts() + " was successfully find") ;
        }
    }
}
