package eu.retarded.internetstore.ui;

import eu.retarded.internetstore.ui.category.AddCategoryUIAction;
import eu.retarded.internetstore.ui.category.DeleteAllCategoryUIAction;
import eu.retarded.internetstore.ui.category.DeleteCategoryUIAction;
import eu.retarded.internetstore.ui.category.ShowAllCategoryUIAction;
import eu.retarded.internetstore.ui.product.AddProductToCategoryUIAction;
import eu.retarded.internetstore.ui.product.DeleteProductFromCategoryUIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


@Component
public class CategoriesMenu implements MenuUIAction {

    private final Map<Integer, UIAction> categoriesMenuNumberToUIActionMap;

    @Autowired
    public CategoriesMenu(List<UIAction> categoriesUIActions) {
        categoriesMenuNumberToUIActionMap = new HashMap<>();
        categoriesMenuNumberToUIActionMap.put(1, findUIAction(categoriesUIActions, AddCategoryUIAction.class));
        categoriesMenuNumberToUIActionMap.put(2, findUIAction(categoriesUIActions, DeleteCategoryUIAction.class));
        categoriesMenuNumberToUIActionMap.put(3, findUIAction(categoriesUIActions, AddProductToCategoryUIAction.class));
        categoriesMenuNumberToUIActionMap.put(4, findUIAction(categoriesUIActions, DeleteProductFromCategoryUIAction.class));
        categoriesMenuNumberToUIActionMap.put(5, findUIAction(categoriesUIActions, DeleteAllCategoryUIAction.class));
        categoriesMenuNumberToUIActionMap.put(6, findUIAction(categoriesUIActions, ShowAllCategoryUIAction.class));
    }

    @Override
    public void execute() {

        while (true) {
            print();
            int menuNumber = getMenuNumberFromUser();
            if (menuNumber == -1) {
                continue;
            }
            if (menuNumber == 0) {
                break;
            }
            executeSelectedMenuItem(menuNumber);
        }
    }


    private UIAction findUIAction(List<UIAction> categoriesUIActions, Class categoriesUIActionClass) {
        return categoriesUIActions.stream()
                .filter(categoriesUIAction -> categoriesUIAction.getClass().equals(categoriesUIActionClass))
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
        System.out.println("[6] - Show all categories");
        System.out.println("[0] - Main menu");
        System.out.println("==========================");
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        String menuNumber = scanner.nextLine();
        menuNumber = menuNumber.replaceAll("[^0-6]", "");
        try {
            return Integer.parseInt(menuNumber);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }


    public void executeSelectedMenuItem(int selectedMenu) {
        categoriesMenuNumberToUIActionMap.get(selectedMenu).execute();
    }

}

