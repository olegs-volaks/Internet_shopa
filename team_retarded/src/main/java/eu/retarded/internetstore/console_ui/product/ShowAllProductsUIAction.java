package eu.retarded.internetstore.console_ui.product;


import eu.retarded.internetstore.console_ui.UIAction;
import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.responses.product.ShowAllProductsResponse;
import eu.retarded.internetstore.core.services.product.ShowAllProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
public class ShowAllProductsUIAction implements UIAction {

    @Autowired
    private ShowAllProductsService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order by name (press 1) or description (press 2): ");
        String orderBy = getChoseNameOrDescription();
        System.out.println("Enter orderDirection ASCENDING (press 1) or DESCENDING (press 2): ");
        String orderDirection = getChoseAscendOrDescend();
        Ordering ordering = new Ordering(orderBy, orderDirection);
        System.out.println("Enter pageNumber: ");
        Integer pageNumber = getPageNumber();
        System.out.println("Enter pageSize: ");
        Integer pageSize = getPageSize();
        Paging paging = new Paging(pageNumber, pageSize);

        ShowAllProductsResponse response = service.execute(new ShowAllProductsRequest());

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field -  "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            System.out.println("All products are successfully found:  ");
            response.getProducts().forEach(System.out::println);
        }
    }

    private String getChoseNameOrDescription() {
        Scanner scanner = new Scanner(System.in);
        String getChose = scanner.nextLine();
        getChose = getChose.replaceAll("\\s+", "");
        int getChoseInt = 0;
        try {
            getChoseInt = Integer.parseInt(getChose);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value");
        }
        if (getChoseInt == 1) {
            return "name";
        } else if (getChoseInt == 2) {
            return "description";
        }
        return null;
    }

    private String getChoseAscendOrDescend() {
        Scanner scanner = new Scanner(System.in);
        String getChose = scanner.nextLine();
        getChose = getChose.replaceAll("\\s+", "");
        int getChoseInt = 0;
        try {
            getChoseInt = Integer.parseInt(getChose);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value ");
        }
        if (getChoseInt == 1) {
            return "ASCENDING";
        } else if (getChoseInt == 2) {
            return "DESCENDING";
        }
        return null;
    }

    private Integer getPageNumber() {
        Scanner scanner = new Scanner(System.in);
        String pageNumber = scanner.nextLine();
        pageNumber = pageNumber.replaceAll("\\s+", "");
        try {
            return Integer.parseInt(pageNumber);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value ");
        }
        return null;
    }

    private Integer getPageSize() {
        Scanner scanner = new Scanner(System.in);
        String pageSize = scanner.nextLine();
        pageSize = pageSize.replaceAll("\\s+", "");
        try {
            return Integer.parseInt(pageSize);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value ");
        }
        return null;
    }
}
