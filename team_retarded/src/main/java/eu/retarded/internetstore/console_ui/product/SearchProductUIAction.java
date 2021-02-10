package eu.retarded.internetstore.console_ui.product;

import eu.retarded.internetstore.console_ui.UIAction;
import eu.retarded.internetstore.core.requests.product.SearchProductRequest;
import eu.retarded.internetstore.core.responses.product.SearchProductResponse;
import eu.retarded.internetstore.core.services.product.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SearchProductUIAction implements UIAction {

    @Autowired
    private SearchProductService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter a search word : ");
        String name = scanner.nextLine();
        //System.out.println("Enter order by name (press 1) or description (press 2): ");
        //String orderBy = getChoseNameOrPrice();
        System.out.println("Enter orderDirection ASCENDING (press 1) or DESCENDING (press 2): ");
        String sorting = getChoseAscendOrDescend();
        System.out.println("Enter page: ");
        int page = getChoseNameOrPrice();

        SearchProductResponse response = service.execute(new SearchProductRequest(name, sorting, page));

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field -  "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            System.out.println("All products are successfully found:  ");
            response.getProducts().forEach(System.out::println);
        }
    }

    private int getChoseNameOrPrice() {
        Scanner scanner = new Scanner(System.in);
        String getChose = scanner.nextLine();
        getChose = getChose.replaceAll("\\s+", "");
        int getChoseInt = 0;
        try {
            getChoseInt = Integer.parseInt(getChose);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value");
        }
        return getChoseInt;
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
            return "ASC";
        } else if (getChoseInt == 2) {
            return "DESC";
        }
        return null;
    }

}




