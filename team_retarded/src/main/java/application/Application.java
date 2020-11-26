package application;

import application.core.services.category.AddCategoryService;
import application.core.services.product.*;
import application.core.services.validators.category.AddCategoryValidator;
import application.core.services.validators.product.*;
import application.database.ListProductDatabase;
import application.database.ProductDatabase;
import application.database.categories.database.CategoriesDatabase;
import application.database.categories.database.ListCategoriesDatabase;
import application.ui.UIAction;
import application.ui.category.AddCategoryUIAction;
import application.ui.product.*;

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
    private static UIAction addCategoryUIAction;


    public static void main(String[] args) {
        initialization();
        while (true) {
            printMenu();
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
                case 9 -> showCategories();
            }
        }

    }

    private static void showCategories() {
        printCategories();
        switch (getChoice()) {
            case 1 -> addCategoryUIAction.execute();
        }
    }

    private static void printMenu() {
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
        System.out.println("[9] - Categories");
        System.out.println("[0] - Exit");
        System.out.println("==========================");
    }

    private static void printCategories() {
        System.out.println("==========================");
        System.out.println("Actions with categories:");
        System.out.println("[1] - Add new category");
        System.out.println("[2] - Delete category");
        System.out.println("[3] - Add product to category");
        System.out.println("[4] - Delete product from category");
        System.out.println("[5] - Delete all categories");
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

    private static void initialization() {
        CategoriesDatabase categoriesDatabase = new ListCategoriesDatabase();
        ProductDatabase productDatabase = new ListProductDatabase(categoriesDatabase);

        AddProductValidator addProductValidator = new AddProductValidator();
        AddProductService addProductService = new AddProductService(productDatabase, addProductValidator);
        addProductUIAction = new AddProductUIAction(addProductService);

        FilterProductsByNameValidator filterProductsByNameValidator = new FilterProductsByNameValidator();
        FilterProductsByNameService filterProductsByNameService = new FilterProductsByNameService(productDatabase,
                filterProductsByNameValidator);
        filterByNameUIAction = new FilterProductsByNameUIAction(filterProductsByNameService);


        FilterProductsByPriceValidator filterProductsByPriceValidator = new FilterProductsByPriceValidator();
        FilterProductsByPriceService filterProductsByPriceService = new FilterProductsByPriceService(productDatabase,
                filterProductsByPriceValidator);
        filterByPriceMinMax = new FilterProductsByPriceUIAction(filterProductsByPriceService);

        GetProductByIdValidator getProductByIdValidator = new GetProductByIdValidator();
        GetProductByIdService getProductByIdService = new GetProductByIdService(productDatabase, getProductByIdValidator);
        getByIdUIAction = new GetProductByIdUIAction(getProductByIdService);


        DeleteProductValidator deleteProductValidator = new DeleteProductValidator();
        DeleteProductService deleteProductService = new DeleteProductService(productDatabase, deleteProductValidator);
        deleteUIAction = new DeleteProductUIAction(deleteProductService);

        exitUIAction = new ExitUIAction();

        ShowAllProductsService showAllProductsService = new ShowAllProductsService(productDatabase);
        getListUIAction = new ShowAllProductsUIAction(showAllProductsService);

        ClearAllProductsService clearAllProductsService = new ClearAllProductsService(productDatabase);
        clearUIAction = new ClearAllProductsUIAction(clearAllProductsService);

        SearchProductValidator searchProductValidator = new SearchProductValidator();
        SearchProductService searchProductService = new SearchProductService(productDatabase, searchProductValidator);
        searchProductUIAction = new SearchProductUIAction(searchProductService);

        AddCategoryValidator addCategoryValidator = new AddCategoryValidator();
        AddCategoryService addCategoryService = new AddCategoryService(categoriesDatabase, addCategoryValidator);
        addCategoryUIAction = new AddCategoryUIAction(addCategoryService);

    }
}
