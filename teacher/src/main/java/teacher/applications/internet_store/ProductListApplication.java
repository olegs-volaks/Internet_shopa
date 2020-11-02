package teacher.applications.internet_store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductListApplication {

	public static void main(String[] args) {
		List<Product> products = new ArrayList<>();

		while (true) {
			System.out.println("Program menu:");
			System.out.println("1. Add product to list");
			System.out.println("2. Delete product from list");
			System.out.println("3. Show all products in the list");
			System.out.println("4. Exit");

			System.out.println("");
			System.out.println("Enter menu item number to execute:");

			Scanner scanner = new Scanner(System.in);
			int userChoice = Integer.parseInt(scanner.nextLine());

			if (userChoice == 4) {
				System.out.println("Good by!");
				break;
			}

			switch (userChoice) {
				case 1: {
					System.out.println("Enter product title: ");
					String productTitle = scanner.nextLine();
					System.out.println("Enter product description: ");
					String productDescription = scanner.nextLine();
					Product product = new Product(productTitle, productDescription);
					products.add(product);
					System.out.println("Your product was added to list.");
					break;
				}
				case 2: {
					System.out.println("Enter product title: ");
					String productTitle = scanner.nextLine();
					System.out.println("Enter product description: ");
					String productDescription = scanner.nextLine();
					products.remove(new Product(productTitle, productDescription));
					System.out.println("Your product was removed from list.");
					break;
				}
				case 3: {
					System.out.println("Product list: ");
					for (Product product : products) {
						System.out.println(product);
					}
					System.out.println("Product list end.");
					break;
				}
			}
			System.out.println("");
		}

	}

}
