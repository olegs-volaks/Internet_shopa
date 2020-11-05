package application;

import java.util.Scanner;

public class ConsoleApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.nextInt() != 0) {
            printMenu();
            scanner.nextInt();


        }

    }

    private static void printMenu () {
        System.out.println("1. Add new item.");
        System.out.println("2. Delete an item by id.");
        System.out.println("3. Delete an item by criteria.");
        System.out.println("0. Exit.");
    }

}
