package application;

import java.util.Scanner;

public class ConsoleApplication {

    public static void main(String[] args) {
        //System.out.println("1. Add new item.");


        Scanner scanner = new Scanner(System.in);
        //if (scanner.nextInt() != 0) {
            do {
                printMenu();
                scanner.nextInt();


            } while (scanner.nextInt() != 0);
        //}

    }

    private static void printMenu () {
        System.out.println("1. Add new item.");
        System.out.println("2. Delete an item by id.");
        System.out.println("3. Delete an item by criteria.");
        System.out.println("0. Exit.");
    }

}
