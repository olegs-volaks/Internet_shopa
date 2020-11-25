package application;

import application.core.services.*;
import application.core.services.validators.*;
import application.database.Database;
import application.database.ProductListDatabase;
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
    private static UIAction searchProductUIAction;


    public static void main(String[] args) {
        initialization();
        while (true) {
            showMenu();
            switch (getChoice()) {
                case 0 -> exitUIAction.execute();
                case 1 -> getListUIAction.execute();
                case 2 -> getByIdUIAction.execute();
                case 3 -> searchProductUIAction.execute();
                case 4 -> addProductUIAction.execute();
                case 5 -> deleteUIAction.execute();
                case 6 -> clearUIAction.execute();
                case 7 -> filterByPriceMinMax.execute();
                case 8 -> filterByNameUIAction.execute();
            }
        }

    }

    private static void initialization() {
        Database db = new ProductListDatabase();
        AddProductValidator addProductValidator = new AddProductValidator();
        AddProductService addProductService = new AddProductService(db, addProductValidator);
        addProductUIAction = new AddProductUIAction(addProductService);

        FilterProductsByNameValidator filterProductsByNameValidator = new FilterProductsByNameValidator();
        FilterProductsByNameService filterProductsByNameService = new FilterProductsByNameService(db, filterProductsByNameValidator);
        filterByNameUIAction = new FilterProductsByNameUIAction(filterProductsByNameService);


        FilterProductsByPriceValidator filterProductsByPriceValidator = new FilterProductsByPriceValidator();
        FilterProductsByPriceService filterProductsByPriceService = new FilterProductsByPriceService(db, filterProductsByPriceValidator);
        filterByPriceMinMax = new FilterProductsByPriceUIAction(filterProductsByPriceService);

        GetProductByIdValidator getProductByIdValidator = new GetProductByIdValidator();
        GetProductByIdService getProductByIdService = new GetProductByIdService(db, getProductByIdValidator);
        getByIdUIAction = new GetProductByIdUIAction(getProductByIdService);


        DeleteProductValidator deleteProductValidator = new DeleteProductValidator();
        DeleteProductService deleteProductService = new DeleteProductService(db, deleteProductValidator);
        deleteUIAction = new DeleteProductUIAction(deleteProductService);

        exitUIAction = new ExitUIAction();

        ShowAllProductsService showAllProductsService = new ShowAllProductsService(db);
        getListUIAction = new ShowAllProductsUIAction(showAllProductsService);

        ClearAllProductsService clearAllProductsService = new ClearAllProductsService(db);
        clearUIAction = new ClearAllProductsUIAction(clearAllProductsService);

        SearchProductValidator searchProductValidator = new SearchProductValidator();
        SearchProductService searchProductService = new SearchProductService(db, searchProductValidator);
        searchProductUIAction = new SearchProductUIAction(searchProductService);

    }

    private static void showMenu() {
        System.out.println("==========================");
        System.out.println("Internet Store MENU:");
        System.out.println("[1] - Show all products");
        System.out.println("[2] - Search by ID");
        System.out.println("[3] - Search product");
        System.out.println("[4] - Add product");
        System.out.println("[5] - Delete product");
        System.out.println("[6] - Delete All products");
        System.out.println("[7] - Filter by price");
        System.out.println("[8] - Filter by names");
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
