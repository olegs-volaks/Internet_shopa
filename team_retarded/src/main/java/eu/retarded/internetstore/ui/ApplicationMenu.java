package eu.retarded.internetstore.ui;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ApplicationMenu {

    private Map<Integer, UIAction> productMenu;


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
        System.out.println("[2] - Add category");;
        System.out.println("[0] - Exit");
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
