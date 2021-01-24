package eu.retarded.internetstore.ui;

import eu.retarded.internetstore.ui.basket.AddProductToBasketUIAction;
import eu.retarded.internetstore.ui.basket.DeleteProductFromBasketUIAction;
import eu.retarded.internetstore.ui.user.AddUserUIAction;
import eu.retarded.internetstore.ui.user.DeleteUserUIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class UserMenu implements MenuUIAction {
    private final Map<Integer, UIAction> userMenuNumberToUIActionMap;

    @Autowired
    public UserMenu(List<UIAction> userMenuUIActions) {
        userMenuNumberToUIActionMap = new HashMap<>();
        userMenuNumberToUIActionMap.put(1, findUIAction(userMenuUIActions, AddUserUIAction.class));
        userMenuNumberToUIActionMap.put(2, findUIAction(userMenuUIActions, DeleteUserUIAction.class));
        userMenuNumberToUIActionMap.put(3, findUIAction(userMenuUIActions, AddProductToBasketUIAction.class));
        userMenuNumberToUIActionMap.put(4, findUIAction(userMenuUIActions, DeleteProductFromBasketUIAction.class));
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

    private UIAction findUIAction(List<UIAction> userMenuUIActions, Class userUIActionClass) {
        return userMenuUIActions.stream()
                .filter(userMenuUIAction -> userMenuUIAction.getClass().equals(userUIActionClass))
                .findFirst()
                .get();
    }

    public void print() {
        System.out.println("==========================");
        System.out.println("Internet Store MENU:");
        System.out.println("[1] - Add user");
        System.out.println("[2] - Delete user");
        System.out.println("[3] - Add product to basket");
        System.out.println("[4] - Remove product from basket");
        System.out.println("[0] - Main menu");
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
        userMenuNumberToUIActionMap.get(selectedMenu).execute();
    }


}

