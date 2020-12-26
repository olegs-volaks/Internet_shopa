package eu.retarded.internetstore.ui;


import eu.retarded.internetstore.ui.category.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class CategoriesMenu {
    private Map<Integer, UIAction> menuNumberToUIActionMap;

    @Autowired
    public CategoriesMenu(List<UIAction> uiActions) {
        menuNumberToUIActionMap = new HashMap<>();
        menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddCategoryUIAction.class));
        menuNumberToUIActionMap.put(2, findUIAction(uiActions, DeleteCategoryUIAction.class));
        menuNumberToUIActionMap.put(3, findUIAction(uiActions, AddProductToCategoryUIAction.class));
        menuNumberToUIActionMap.put(4, findUIAction(uiActions, DeleteProductFromCategoryUIAction.class));
        menuNumberToUIActionMap.put(5, findUIAction(uiActions, DeleteAllCategoryUIAction.class));
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
        System.out.println("Actions with categories:");
        System.out.println("[1] - Add new category");
        System.out.println("[2] - Delete category");
        System.out.println("[3] - Add product to category");
        System.out.println("[4] - Delete product from category");
        System.out.println("[5] - Delete all categories");
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

