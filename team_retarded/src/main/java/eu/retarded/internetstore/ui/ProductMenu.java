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

    private Map<Integer, UIAction> productMenu;

    @Autowired
    public void ProductMenu(List<UIAction> uiActions) {
        productMenu = new HashMap<>();
        productMenu.put(1, findUIAction(uiActions, AddProductUIAction.class));
        productMenu.put(2, findUIAction(uiActions, DeleteProductUIAction.class));
        productMenu.put(3, findUIAction(uiActions, GetProductByIdUIAction.class));
        productMenu.put(4, findUIAction(uiActions, SearchProductUIAction.class));
        productMenu.put(5, findUIAction(uiActions, ClearAllProductsUIAction.class));
        productMenu.put(6, findUIAction(uiActions, ShowAllProductsUIAction.class));
        productMenu.put(0, findUIAction(uiActions, ExitUIAction.class));

    }
    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printMenu() {

        System.out.println("==========================");
        System.out.println("Internet Store MENU:");
        System.out.println("[1] - Add product");
        System.out.println("[2] - Delete product");
        System.out.println("[3] - Show product by ID");
        System.out.println("[4] - Search product");
        System.out.println("[5] - Delete all products");
        System.out.println("[6] - Show all products");
        System.out.println("[0] - Exit to main menu");
        System.out.println("==========================");
    }

    public int getChoice() {
        System.out.print("Please, enter menu item number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            return Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }
}
