package eu.retarded.internetstore.ui;

import eu.retarded.internetstore.ui.category.*;
import eu.retarded.internetstore.ui.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ApplicationMenu {

    private Map<Integer, UIAction> productMenu;

    @Autowired
    public void ProductMenu(List<UIAction> uiActions) {
        productMenu = new HashMap<>();
        productMenu.put(1, findUIAction(uiActions, AddProductUIAction.class));
        productMenu.put(2, findUIAction(uiActions, DeleteProductUIAction.class));
        productMenu.put(3, findUIAction(uiActions, GetProductByIdUIAction.class));
        productMenu.put(4, findUIAction(uiActions, SearchProductUIAction.class));
        productMenu.put(5, findUIAction(uiActions, AddCategoryUIAction.class)); // не уверен что этот класс нужно сюда пихать
        productMenu.put(6, findUIAction(uiActions, ClearAllProductsUIAction.class));
        productMenu.put(7, findUIAction(uiActions, ShowAllProductsUIAction.class));
        productMenu.put(0, findUIAction(uiActions, ExitUIAction.class));

    }

    private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(uiActionClass))
                .findFirst()
                .get();
    }

    public void CategoryMenu(List<UIAction> uiActions) {
        Map<Integer, UIAction> categoryMenu = new HashMap<>();
        categoryMenu.put(1,findUIAction(uiActions,AddCategoryUIAction.class));
        categoryMenu.put(2,findUIAction(uiActions, DeleteCategoryUIAction.class));
        categoryMenu.put(3,findUIAction(uiActions, AddProductToCategoryUIAction.class));
        categoryMenu.put(4,findUIAction(uiActions, DeleteProductFromCategoryUIAction.class));
        categoryMenu.put(5,findUIAction(uiActions, DeleteAllCategoryUIAction.class));
    }
    

    public void printMenu() {

        System.out.println("==========================");
        System.out.println("Internet Store MENU:");
        System.out.println("[3] - Show product by ID");
        System.out.println("[4] - Search product");
        System.out.println("[1] - Add product");
        System.out.println("[2] - Delete product");
        System.out.println("[5] - Product categories");
        System.out.println("[6] - Delete all products");
        System.out.println("[7] - Show all products");
        System.out.println("[0] - Exit");
        System.out.println("==========================");
    }
    
    public void printCategories() {
        System.out.println("==========================");
        System.out.println("Actions with categories:");
        System.out.println("[1] - Add new category");
        System.out.println("[2] - Delete category");
        System.out.println("[3] - Add product to category");
        System.out.println("[4] - Delete product from category");
        System.out.println("[5] - Delete all categories");
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
    

    public void executeSelectedMenu(int selectedMenu) {
        productMenu.get(selectedMenu).execute();
    }


}
