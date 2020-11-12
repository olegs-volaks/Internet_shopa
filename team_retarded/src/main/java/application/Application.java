package application;

import application.bd.Database;
import application.bd.ProductListDatabase;
import application.services.*;
import application.ui.*;
import java.util.Scanner;

public class Application {

    private static Database db;
    private static UIAction addProductUIAction;
    private static UIAction filterUIAction;
    private static UIAction getByIdUIAction;
    private static UIAction deleteUIAction;
    private static UIAction exitUIAction;
    private static UIAction getListUIAction;


    public static void main(String[] args) {
        initialization();
        while (true) {
            showMenu();
            switch (getChoice()) {
                case 0 : {
                    exitUIAction.execute();
                    break;
                }
                case 1 : {
                    getListUIAction.execute();
                    break;
                }
                case 2 : {
                    getByIdUIAction.execute();
                    break;
                }
                case 3 : {
                    filterUIAction.execute();
                    break;
                }
                case 4 :{
                    addProductUIAction.execute();
                    break;
                }
                case 5 : {
                    deleteUIAction.execute();
                    break;
                }
            }
        }

    }


    private static void initialization() {
        db = new ProductListDatabase();
        AddProductService addProductService = new AddProductService(db);
        DeleteProductService deleteProductService = new DeleteProductService(db);
        ShowProductByIDService showProductByIDService = new ShowProductByIDService(db);
        ShowAllProductService showAllProductService = new ShowAllProductService(db);
        FilterService filterService = new FilterService(db);
        addProductUIAction = new AddProductUIAction(addProductService);
        filterUIAction= new FilterByNameUIAction(filterService);
        getByIdUIAction= new ShowProductByIDAction(showProductByIDService);
        deleteUIAction = new DeleteProductUIAction(deleteProductService);
        exitUIAction = new ExitUIAction();
        getListUIAction = new ShowAllProductUIAction(showAllProductService);
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
            return Integer.parseInt(scanner.nextLine().replaceAll("\\s+",""));
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }
}
