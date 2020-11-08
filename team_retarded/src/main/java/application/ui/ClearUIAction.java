package application.ui;

import application.bd.Database;

import java.util.Scanner;

public class ClearUIAction implements UIAction {

    private Database db;

    public ClearUIAction(Database db) {
        this.db = db;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Clear list : ");
        db.clear();
        System.out.println("Your clear a list");
    }
}
