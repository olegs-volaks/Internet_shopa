package application;

import application.bd.Database;
import application.bd.ProductListDatabase;
import application.services.*;
import application.ui.*;

import java.util.Scanner;

public class Application {

    //private static Database db;
    private static Database db = new ProductListDatabase();
    private static  AddProductService addProductService = new AddProductService(db);
    private static UIAction addProductUIAction = new AddProductUIAction(addProductService);
    private static UIAction filterByNameUIAction;
    private static UIAction getByIdUIAction;
    private static UIAction deleteUIAction;
    private static UIAction exitUIAction;
    private static UIAction getListUIAction;
    private static UIAction clearUIAction;


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
                    filterByNameUIAction.execute();
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
        addProductUIAction = new AddProductUIAction(addProductService);

        FilterService filterService= new FilterService(db);
        filterByNameUIAction = new FilterByNameUIAction(filterService);

        GetByIdService getByIdService= new GetByIdService(db);
        getByIdUIAction =new GetByIdUIAction(getByIdService);

        DeleteProductService deleteProductService = new DeleteProductService(db);
        deleteUIAction = new DeleteProductUIAction(deleteProductService);

        exitUIAction = new ExitUIAction();

        ShowAllProductService showAllProductService= new ShowAllProductService(db);
        getListUIAction = new ShowAllProductUIAction(showAllProductService);

        ClearService clearService = new ClearService(db);
        clearUIAction = new ClearUIAction(clearService);
    }


    private static void showMenu() {
        System.out.println("==========================");
        System.out.println("Internet Store MENU:");
        System.out.println("[1] - Show all products");
        System.out.println("[2] - Search by ID");
        System.out.println("[3] - Filter by name");
        System.out.println("[4] - Add product");
        System.out.println("[5] - Delete product");
        System.out.println("[6] - Delete All products");
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
