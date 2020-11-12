package application.ui;

import application.items.Product;
import application.services.FilterService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;


public class FilterByPriceMinMaxUIAction implements UIAction {
    private final FilterService filterService;


    public FilterByPriceMinMaxUIAction(FilterService filterService) {
        this.filterService = filterService;
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
        List<Product> products = filterService.Filter(product -> product.getPrice().compareTo(new BigDecimal(Double.toString(finalPriceMin))) > 0 &&
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
