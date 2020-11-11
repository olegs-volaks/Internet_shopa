package application.ui;

import application.items.Product;
import application.services.FilterService;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.function.Predicate;

public class FilterByPriceMinMaxUIAction implements UIAction {
    private final FilterService filterService;
    public Predicate<Product> predicate ;

    public FilterByPriceMinMaxUIAction(FilterService filterService) {
        this.filterService = filterService;
    }

    @Override
    public void execute() {

       /* Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter new product min price: ");
        double priceMin;
        double priceMax;
        do {
            priceMin = getPriceMin();
            priceMax = getPriceMax();
        } while (priceMin < 0 && priceMax < priceMin);
        System.out.print("Please, enter another product max price: ");
        String name1 = scanner.nextLine();
        System.out.println("All products are successfully found:  ");
        for (int i = 0; i < filterService.Filter(product -> product.getPrice().compareTo(new BigDecimal(Double.toString(priceMin)) > 0 &&
                product.getPrice().compareTo(new BigDecimal(Double.toString(priceMin)) < 0))).size(); i++) {
            System.out.println(filterService.Filter(product -> product.getPrice().compareTo(new BigDecimal(Double.toString(priceMin)) > 0 &&
                            product.getPrice().compareTo(new BigDecimal(Double.toString(priceMin)) < 0))).toString());
        }*/
    }

    private double getPriceMin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter min price: ");
        String price = scanner.nextLine();
        price = price.replaceAll("\\s+","");
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
        price = price.replaceAll("\\s+","");
        try {
            return Double.parseDouble(price);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }
}
