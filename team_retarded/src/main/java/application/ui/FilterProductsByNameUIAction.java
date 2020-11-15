package application.ui;

import application.core.services.FilterProductsByNameService;
import application.items.Product;

import java.util.List;
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
        String name = scanner.nextLine();
        System.out.print("Please, enter another product name: ");
        String name1 = scanner.nextLine();
        System.out.println("All products are successfully found:  ");
        List<Product> products = service.Filter(product -> product.getName().contains(name) || product.getName().contains(name1));
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
    }
}

