package application;

import application.bd.Database;
import application.bd.ProductListDatabase;
import application.core.services.*;
import application.core.services.validators.AddProductValidator;
import application.core.services.validators.DeleteProductValidator;
import application.core.services.validators.FilterProductsByPriceValidator;
import application.ui.*;

import java.util.Scanner;

public class Application {

    private static UIAction addProductUIAction;
    private static UIAction filterByNameUIAction;
    private static UIAction getByIdUIAction;
    private static UIAction deleteUIAction;
    private static UIAction exitUIAction;
    private static UIAction getListUIAction;
    private static UIAction filterByPriceMinMax;
    private static UIAction clearUIAction;


    public static void main(String[] args) {
        initialization();
        while (true) {
            showMenu();
            switch (getChoice()) {
                case 0 -> exitUIAction.execute();
                case 1 -> getListUIAction.execute();
                case 2 -> getByIdUIAction.execute();
                case 3 -> filterByNameUIAction.execute();
                case 4 -> addProductUIAction.execute();
                case 5 -> deleteUIAction.execute();
                case 6 -> clearUIAction.execute();
                case 7 -> filterByPriceMinMax.execute();
            }
        }

    }

    private static void initialization() {
        Database db = new ProductListDatabase();
        AddProductValidator addProductValidator = new AddProductValidator();
        AddProductService addProductService = new AddProductService(db, addProductValidator);
        addProductUIAction = new AddProductUIAction(addProductService);

        FilterProductsByNameService filterProductsByNameService = new FilterProductsByNameService(db);
        filterByNameUIAction = new FilterProductsByNameUIAction(filterProductsByNameService);


        FilterProductsByPriceValidator filterProductsByPriceValidator = new FilterProductsByPriceValidator();
        FilterProductsByPriceService filterProductsByPriceService = new FilterProductsByPriceService(db, filterProductsByPriceValidator);
        filterByPriceMinMax = new FilterProductsByPriceUIAction(filterProductsByPriceService);

        GetProductByIdService getProductByIdService = new GetProductByIdService(db);
        getByIdUIAction = new GetProductByIdUIAction(getProductByIdService);


        DeleteProductValidator deleteProductValidator = new DeleteProductValidator();
        DeleteProductService deleteProductService = new DeleteProductService(db, deleteProductValidator);
        deleteUIAction = new DeleteProductUIAction(deleteProductService);

        exitUIAction = new ExitUIAction();

        ShowAllProductsService showAllProductsService = new ShowAllProductsService(db);
        getListUIAction = new ShowAllProductsUIAction(showAllProductsService);

        ClearAllProductsService clearAllProductsService = new ClearAllProductsService(db);
        clearUIAction = new ClearAllProductsUIAction(clearAllProductsService);


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
        System.out.println("[7] - Filter by price");
        System.out.println("[0] - Exit");
        System.out.println("==========================");
    }

    private static int getChoice() {
        System.out.print("Please, enter menu item number: ");
        Scanner scanner = new Scanner(System.in);
        try {
            return Integer.parseInt(scanner.nextLine().replaceAll("\\s+", ""));
        } catch (NumberFormatException ex) {
            System.out.println("Incorrect value, try again ");
        }
        return -1;
    }
}
