package eu.retarded.internetstore;

import eu.retarded.internetstore.config.applicationConfiguration;
import eu.retarded.internetstore.ui.category.AddCategoryUIAction;
import eu.retarded.internetstore.ui.category.AddProductToCategoryUIAction;
import eu.retarded.internetstore.ui.category.DeleteCategoryUIAction;
import eu.retarded.internetstore.ui.category.DeleteProductFromCategoryUIAction;
import eu.retarded.internetstore.ui.product.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class Application {


    private static ApplicationContext CONTEXT = new AnnotationConfigApplicationContext(applicationConfiguration.class);

    public static void main(String[] args) {
        while (true) {
            printMenu();

            switch (getChoice()) {
                case 0 -> {
                    ExitUIAction exitUIAction = CONTEXT.getBean(ExitUIAction.class);
                    exitUIAction.execute();
                }
                case 1 -> {
                    AddProductUIAction addProductUIAction = CONTEXT.getBean(AddProductUIAction.class);
                    addProductUIAction.execute();
                }
                case 2 -> {
                    DeleteProductUIAction deleteProductUIAction = CONTEXT.getBean(DeleteProductUIAction.class);
                    deleteProductUIAction.execute();
                }
                case 3 -> {
                    GetProductByIdUIAction getProductByIdUIAction = CONTEXT.getBean(GetProductByIdUIAction.class);
                    getProductByIdUIAction.execute();
                }
                case 4 -> {
                    SearchProductUIAction searchProductUIAction = CONTEXT.getBean(SearchProductUIAction.class);
                    searchProductUIAction.execute();
                }
                case 5 -> showCategories();
                case 6 -> {
                    ClearAllProductsUIAction clearAllProductsUIAction = CONTEXT.getBean(ClearAllProductsUIAction.class);
                    clearAllProductsUIAction.execute();
                }
                case 7 -> {
                    ShowAllProductsUIAction showAllProductsUIAction = CONTEXT.getBean(ShowAllProductsUIAction.class);
                    showAllProductsUIAction.execute();
                }
            }
        }

    }

    private static void showCategories() {
        printCategories();
        switch (getChoice()) {
            case 1 -> {
                AddCategoryUIAction addCategoryUIAction = CONTEXT.getBean(AddCategoryUIAction.class);
                addCategoryUIAction.execute();
            }
            case 2 -> {
                DeleteCategoryUIAction deleteCategoryUIAction = CONTEXT.getBean(DeleteCategoryUIAction.class);
                deleteCategoryUIAction.execute();
            }
            case 3 -> {
                AddProductToCategoryUIAction addProductToCategoryUIAction = CONTEXT.getBean(AddProductToCategoryUIAction.class);
                addProductToCategoryUIAction.execute();
            }
            case 4 -> {
                DeleteProductFromCategoryUIAction deleteProductFromCategoryUIAction = CONTEXT.getBean(DeleteProductFromCategoryUIAction.class);
                deleteProductFromCategoryUIAction.execute();
            }
        }
    }

    private static void printMenu() {

        System.out.println("==========================");
        System.out.println("Internet Store MENU:");
        System.out.println("[1] - Add product");
        System.out.println("[2] - Delete product");
        System.out.println("[3] - Show product by ID");
        System.out.println("[4] - Search product");
        System.out.println("[5] - Product categories");
        System.out.println("[6] - Delete all products");
        System.out.println("[7] - Show all products");
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
}
