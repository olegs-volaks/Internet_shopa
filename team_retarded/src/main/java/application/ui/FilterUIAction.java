package application.ui;

import application.bd.Database;
import application.items.Product;
import application.services.FilterService;

import java.util.Scanner;

public class FilterUIAction implements UIAction {

    private final FilterService filterService;

    public FilterUIAction(FilterService filterService) {
        this.filterService = filterService;
    }

    @Override
    public void execute() {
        System.out.println("Make filter of all products: ");

    }
}
