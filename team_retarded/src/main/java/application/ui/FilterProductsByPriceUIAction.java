package application.ui;

import application.core.requests.FilterProductsByPriceRequest;
import application.core.responses.FilterProductsByPriceResponse;
import application.core.services.FilterProductsByPriceService;

import java.util.Scanner;


public class FilterProductsByPriceUIAction implements UIAction {
    private final FilterProductsByPriceService service;


    public FilterProductsByPriceUIAction(FilterProductsByPriceService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        double priceMin;
        double priceMax;
        do {
            priceMin = getPriceMin();
            priceMax = getPriceMax();
        } while (priceMin < 0 && priceMax < priceMin);


        FilterProductsByPriceResponse response = service.execute(new FilterProductsByPriceRequest(priceMin, priceMax));
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> System.out.println("Error in the field - "
                    + coreError.getField() + ": " + coreError.getMessage()));
        } else {
            System.out.println("All products are successfully found:  ");
            response.getProductsByFilter().forEach(System.out::println);
        }

    }

    private double getPriceMin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter min price: ");
        String price = scanner.nextLine();
        price = price.replaceAll("\\s+", "");
        try {
            return Double.parseDouble(price);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }

    private double getPriceMax() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter max price: ");
        String price = scanner.nextLine();
        price = price.replaceAll("\\s+", "");
        try {
            return Double.parseDouble(price);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }
}
