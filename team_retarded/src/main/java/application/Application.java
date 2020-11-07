package application;

import application.bd.Database;
import application.bd.ProductListDatabase;
import application.items.Product;
import application.services.AddProductService;
import application.ui.AddProductUIAction;
import application.ui.UIAction;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Application {

    private static UIAction addProductUIAction;
    private static Database db;

    public static void main(String[] args) {
        initialization();
        while (true) {
            showMenu();
            switch (getChoice()) {
                case 0: {
                    exit();
                    break;
                }
                case 1: {
                    showAllProduct(db.getList());
                    break;
                }
               /* case 2: {
                    searchById(db.getById(1L));
                    break;
                }*/
                case 3:

                    break;
                case 4: {
                    addProductUIAction.execute();
                    break;
                }
                case 5: {
                    deleteProduct();
                    break;
                }
            }

        }

    }



    private static void initialization() {
        db = new ProductListDatabase();
        AddProductService addProductService = new AddProductService(db);
        addProductUIAction = new AddProductUIAction(addProductService);
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

    private static void exit() {
        System.out.println("Good Bye!");
        System.exit(0);
    }

    private static void showAllProduct(List<Product> products) {
        System.out.println("Show all product list");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("Product list end ");
    }

   /* private static void searchById(Optional<Product> byId) {
        System.out.println("Search product by ID");
        for (Product product : byId) {
            System.out.println(product);
        }
        System.out.println("ID search end ");
    }*/

    private static void filter() {

    }

    private static void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your product ");
        String addProduct = scanner.nextLine();
        db.add("Iphone","X ",350);
        System.out.println("Your product was added to list.");
    }

    private static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your product title");
        String deleteProduct = scanner.nextLine();
        db.delete(2l);
        System.out.println("Your product was removed from list.");
    }
}
