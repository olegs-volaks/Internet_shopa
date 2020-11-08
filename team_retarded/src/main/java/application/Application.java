package application;

import application.bd.Database;
import application.bd.ProductListDatabase;
import application.items.Product;
import application.services.AddProductService;
import application.ui.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Application {

    private static Database db;
    private static UIAction addProductUIAction;
    private static UIAction filter;
    private static UIAction getById;
    private static UIAction delete;
    private static UIAction exit;
    private static UIAction getList;


    public static void main(String[] args) {
        initialization();
        while (true) {
            showMenu();
            int menuNumber = getChoice();
            switch (getChoice()) {
                case 0 : {
                    exit.execute();
                    break;
                }
                case 1 : {
                    getList.execute();
                    break;
                }
                case 2 : {
                    getById.execute();
                    break;
                }
                case 3 : {
                    filter.execute();
                    break;
                }
                case 4 :{
                    addProductUIAction.execute();
                    break;
                }
                case 5 : {
                    delete.execute();
                    break;
                }
            }
        }

    }


    private static void initialization() {
        db = new ProductListDatabase();
        AddProductService addProductService = new AddProductService(db);
        addProductUIAction = new AddProductUIAction(addProductService);
        UIAction filter = new FilterUIAction(db);
        UIAction getById = new GetByIdUIAction(db);
        UIAction delete = new DeleteProductUIAction(db);
        UIAction exit = new ExitUIAction();
        UIAction getList = new ShowAllProductUIAction(db);
    }

    private static void showMenu() {
        System.out.println("==========================");
        System.out.println("Internet Store MENU:");
        System.out.println("[1] - Show all products");
        System.out.println("[2] - Search by ID");
        System.out.println("[3] - Filter");
        System.out.println("[4] - Add product");
        System.out.println("[5] - Delete product");
        System.out.println("[0] - Exit");
        System.out.println("==========================");
    }

    private static int getChoice() {
        System.out.print("Please, enter menu item number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again");
        }
        return -1;
    }


}
