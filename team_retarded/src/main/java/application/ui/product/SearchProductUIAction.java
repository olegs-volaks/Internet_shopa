package application.ui.product;

import application.core.requests.product.Ordering;
import application.core.requests.product.Paging;
import application.core.requests.product.SearchProductRequest;
import application.core.responses.product.SearchProductResponse;
import application.core.services.product.SearchProductService;
import application.ui.UIAction;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.Scanner;

@DIComponent
public class SearchProductUIAction implements UIAction {

    @DIDependency
    private SearchProductService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter a product name : ");
        String name = scanner.nextLine();
        System.out.println("Please, enter a product description: ");
        String description = scanner.nextLine();
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

        SearchProductResponse response = service.execute(new SearchProductRequest(name, description, ordering, paging));

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




