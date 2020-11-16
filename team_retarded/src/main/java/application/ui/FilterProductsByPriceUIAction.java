package application.ui;

import application.core.services.FilterProductsByPriceService;
import application.items.Product;

import java.math.BigDecimal;
import java.util.List;
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

        System.out.println("All products are successfully found:  ");
        double finalPriceMax = priceMax;
        double finalPriceMin = priceMin;
        List<Product> products = service.execute(product -> product.getPrice().compareTo(new BigDecimal(Double.toString(finalPriceMin))) > 0 &&
                product.getPrice().compareTo(new BigDecimal(Double.toString(finalPriceMax))) < 0);
        for (Product product : products) {
            System.out.println(product);
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
