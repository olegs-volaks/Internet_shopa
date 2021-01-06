package eu.retarded.internetstore.ui;

import eu.retarded.internetstore.ui.category.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class CategoryMenu {

    private Map<Integer, UIAction> categoryMenu;

    @Autowired
    public void CategoryMenu(List<UIAction> uiActions) {
        Map<Integer, UIAction> categoryMenu = new HashMap<>();
        categoryMenu.put(1,findUIAction(uiActions, AddCategoryUIAction.class));
        categoryMenu.put(2,findUIAction(uiActions, DeleteCategoryUIAction.class));
        categoryMenu.put(3,findUIAction(uiActions, AddProductToCategoryUIAction.class));
        categoryMenu.put(4,findUIAction(uiActions, DeleteProductFromCategoryUIAction.class));
        categoryMenu.put(5,findUIAction(uiActions, DeleteAllCategoryUIAction.class));
    }
    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void printMenu() {
        System.out.println("==========================");
        System.out.println("Actions with categories:");
        System.out.println("[1] - Add new category");
        System.out.println("[2] - Delete category");
        System.out.println("[3] - Add product to category");
        System.out.println("[4] - Delete product from category");
        System.out.println("[5] - Delete all categories");
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
