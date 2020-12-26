package eu.retarded.internetstore.ui;


import eu.retarded.internetstore.ui.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProductMenu {
   private Map<Integer, UIAction> menuNumberToUIActionMap;

    @Autowired
    public ProductMenu(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddProductUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, DeleteProductUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, GetProductByIdUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, SearchProductUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, ClearAllProductsUIAction.class));
        menuNumberToUIActionMap.put(6, findUIAction(uiActions, ShowAllProductsUIAction.class));
        //menuNumberToUIActionMap.put(0, findUIAction(uiActions, ProgramMenu.class));

    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
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
        menuNumberToUIActionMap.get(selectedMenu).execute();
    }

}
