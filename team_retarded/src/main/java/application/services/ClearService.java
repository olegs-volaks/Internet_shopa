package application.services;

import application.bd.Database;

public class ClearService {
    private final Database db;

    public ClearService(Database db) {
        this.db = db;
    }

    public void clear() {
        db.clear();
    }
}
