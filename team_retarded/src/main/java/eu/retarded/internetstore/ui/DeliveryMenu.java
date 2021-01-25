package eu.retarded.internetstore.ui;

import eu.retarded.internetstore.ui.delivery.AddDeliveryUIAction;
import eu.retarded.internetstore.ui.delivery.DeleteDeliveryUIAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class DeliveryMenu implements MenuUIAction {
    private final Map<Integer, UIAction> deliveryMenuNumberToUIActionMap;

    @Autowired
    public DeliveryMenu(List<UIAction> deliveryMenuUIActions) {
        deliveryMenuNumberToUIActionMap = new HashMap<>();
        deliveryMenuNumberToUIActionMap.put(1, findUIAction(deliveryMenuUIActions, AddDeliveryUIAction.class));
        deliveryMenuNumberToUIActionMap.put(2, findUIAction(deliveryMenuUIActions, DeleteDeliveryUIAction.class));
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

    private UIAction findUIAction(List<UIAction> deliveryMenuUIActions, Class deliveryUIActionClass) {
        return deliveryMenuUIActions.stream()
                .filter(deliveryMenuUIAction -> deliveryMenuUIAction.getClass().equals(deliveryUIActionClass))
                .findFirst()
                .get();
    }

    public void print() {
        System.out.println("==========================");
        System.out.println("Internet Store MENU:");
        System.out.println("[1] - Add delivery");
        System.out.println("[2] - Delete delivery");
        System.out.println("[0] - Main menu");
        System.out.println("==========================");
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        String menuNumber = scanner.nextLine();
        menuNumber = menuNumber.replaceAll("[^0-2]", "");
        try {
            return Integer.parseInt(menuNumber);
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        deliveryMenuNumberToUIActionMap.get(selectedMenu).execute();
    }


}
