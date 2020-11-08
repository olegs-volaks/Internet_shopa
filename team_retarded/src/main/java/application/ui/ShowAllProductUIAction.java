package application.ui;

import application.bd.Database;

public class ShowAllProductUIAction  implements UIAction {

    private Database db;

    public ShowAllProductUIAction(Database db) {
        this.db = db;
    }

    @Override
    public void execute() {
        db.getList();
    }
}
