package eu.retarded.internetstore.ui;

import eu.retarded.internetstore.ui.product.ExitUIAction;
import eu.retarded.internetstore.ui.product.ProductMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProgramMenu {

    private final Map<Integer, UIAction> ProgramMenuNumberToUIActionMap;

    @Autowired
    public ProgramMenu( List<UIAction> uiActions) {
        ProgramMenuNumberToUIActionMap = new HashMap<>();
        ProgramMenuNumberToUIActionMap.put(1, findUIAction(uiActions, ProductMenu.class));
        //ProgramMenuNumberToUIActionMap.put( 2, findUIAction(uiActions, CategoriesMenu.class));
        ProgramMenuNumberToUIActionMap.put(0, findUIAction(uiActions, ExitUIAction.class));
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
        System.out.println("[1] - Product  menu");
        System.out.println("[2] - Categories menu");
        System.out.println("[0] - Exit");
        System.out.println("==========================");
    }

    public int getMenuNumberFromUser() {
        System.out.println("Enter menu item number to execute:");
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.nextLine());
    }

    public void executeSelectedMenuItem(int selectedMenu) {
        ProgramMenuNumberToUIActionMap.get(selectedMenu).execute();
    }
}
