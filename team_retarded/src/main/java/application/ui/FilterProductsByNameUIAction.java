package application.ui;

import application.core.requests.FilterProductsByNameRequest;
import application.core.responses.FilterProductsByNameResponse;
import application.core.services.FilterProductsByNameService;

import java.util.Scanner;

public class FilterProductsByNameUIAction implements UIAction {
    private final FilterProductsByNameService service;

    public FilterProductsByNameUIAction(FilterProductsByNameService service) {
        this.service = service;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter new product name: ");
        String name1 = scanner.nextLine();
        System.out.print("Please, enter another product name: ");
        String name2 = scanner.nextLine();

        FilterProductsByNameResponse response = service.execute(new FilterProductsByNameRequest(name1, name2));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            System.out.println("All products are successfully found:  ");
            response.getFilterProductsByName().forEach(System.out::println);
        }
    }

}


