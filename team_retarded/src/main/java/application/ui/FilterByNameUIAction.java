package application.ui;

import application.items.Product;
import application.services.FilterService;


import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class FilterByNameUIAction implements UIAction {
    private final FilterService filterService;

    public FilterByNameUIAction(FilterService filterService) {
        this.filterService = filterService;
    }

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Please, enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Please, enter another product name: ");
        String name1 = scanner.nextLine();
        System.out.println("All products are successfully found:  ");
        List<Product> products = filterService.Filter(product -> product.getName().contains(name) || product.getName().contains(name1));
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
    }
}

