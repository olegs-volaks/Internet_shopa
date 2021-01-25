package eu.retarded.internetstore.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProgramMenu {

    private final Map<Integer, MenuUIAction> ProgramMenuNumberToUIActionMap;

    @Autowired
    public ProgramMenu(List<MenuUIAction> uiActions) {
        ProgramMenuNumberToUIActionMap = new HashMap<>();
        ProgramMenuNumberToUIActionMap.put(1, findUIAction(uiActions, ProductMenu.class));
        ProgramMenuNumberToUIActionMap.put(2, findUIAction(uiActions, CategoriesMenu.class));
        ProgramMenuNumberToUIActionMap.put(3, findUIAction(uiActions, UserMenu.class));
        ProgramMenuNumberToUIActionMap.put(4, findUIAction(uiActions, DeliveryMenu.class));
        ProgramMenuNumberToUIActionMap.put(0, findUIAction(uiActions, ExitUIAction.class));
    }


    private MenuUIAction findUIAction(List<MenuUIAction> uiActions, Class MenuUIActionClass) {
        return uiActions.stream()
                .filter(uiAction -> uiAction.getClass().equals(MenuUIActionClass))
                .findFirst()
                .get();
    }

    public void print() {
        System.out.println("==========================");
        System.out.println("Internet Store MENU:");
        System.out.println("[1] - Product  menu");
        System.out.println("[2] - Categories menu");
        System.out.println("[3] - Users menu");
        System.out.println("[4] - Delivery menu");
        System.out.println("[0] - Exit");
        System.out.println("==========================");
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        String menuNumber = scanner.nextLine();
        menuNumber = menuNumber.replaceAll("[^0-4]", "");
        try {
            return Integer.parseInt(menuNumber);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        ProgramMenuNumberToUIActionMap.get(selectedMenu).execute();
    }
}
