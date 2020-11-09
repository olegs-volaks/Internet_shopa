package application.services;

import application.bd.Database;

public class GetByIDService {

    private final Database db;

    public GetByIDService(Database db) {
        this.db = db;
    }

    public void getById(Long id) {
        db.getById(id);
    }
}
