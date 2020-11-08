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
        System.out.println("Please, clear your list : ");
        db.clear();
        System.out.println("Your list is successfully cleared .  ");
    }
}
