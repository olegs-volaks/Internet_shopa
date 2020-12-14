package application.ui.product;


import application.core.services.product.ClearAllProductsService;
import application.ui.UIAction;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;

import java.util.Scanner;

@DIComponent
public class ClearAllProductsUIAction implements UIAction {

    @DIDependency
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
