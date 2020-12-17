package eu.retarded.internetstore.ui.product;


import eu.retarded.internetstore.core.services.product.ClearAllProductsService;
import eu.retarded.internetstore.ui.UIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ClearAllProductsUIAction implements UIAction {

    @Autowired
    private ClearAllProductsService service;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Are you sure? You want to delete all products?");
        System.out.print("Enter \"Y\" to confirm or any other to cancel: ");
        String result = scanner.nextLine();
        result = result.replaceAll("\\s+", "")
                .toLowerCase();
        if (result.equals("y")) {
            service.execute();
            System.out.println("Your list is successfully cleared ");
        }
    }
}
