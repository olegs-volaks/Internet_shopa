package eu.retarded.internetstore.ui;


import eu.retarded.internetstore.ui.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


@Component

public class ProductMenu implements MenuUIAction {
   private  Map<Integer, UIAction> productMenuNumberToUIActionMap;

   @Autowired
    public ProductMenu( List<UIAction> productMenuUIActions) {
       productMenuNumberToUIActionMap = new HashMap<>();
       productMenuNumberToUIActionMap.put(1, findUIAction(productMenuUIActions, AddProductUIAction.class));
       productMenuNumberToUIActionMap.put(2, findUIAction(productMenuUIActions, DeleteProductUIAction.class));
       productMenuNumberToUIActionMap.put(3, findUIAction(productMenuUIActions, GetProductByIdUIAction.class));
       productMenuNumberToUIActionMap.put(4, findUIAction(productMenuUIActions, SearchProductUIAction.class));
       productMenuNumberToUIActionMap.put(5, findUIAction(productMenuUIActions, ClearAllProductsUIAction.class));
       productMenuNumberToUIActionMap.put(6, findUIAction(productMenuUIActions, ShowAllProductsUIAction.class));

    }


    public void execute() {

        while (true) {
            print();
            int menuNumber = getMenuNumberFromUser();
            if (menuNumber==0){
                break;
            }
            executeSelectedMenuItem(menuNumber);

        }
    }

    private UIAction findUIAction(List<UIAction> productMenuUIActions, Class productUIActionClass) {
        return productMenuUIActions.stream()
                .filter(productMenuUIAction -> productMenuUIAction.getClass().equals(productUIActionClass))
                .findFirst()
                .get();
    }

    public void print() {
        System.out.println("==========================");
        System.out.println("Internet Store MENU:");
        System.out.println("[1] - Add product");
        System.out.println("[2] - Delete product");
        System.out.println("[3] - Show product by ID");
        System.out.println("[4] - Search product");
        System.out.println("[5] - Delete all products");
        System.out.println("[6] - Show all products");
        System.out.println("[0] - Main menu");
        System.out.println("==========================");
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        productMenuNumberToUIActionMap.get(selectedMenu).execute();
    }


}
