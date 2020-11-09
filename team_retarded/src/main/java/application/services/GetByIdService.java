package application.services;

import application.bd.Database;

public class GetByIdService {
    private final Database db;

    public GetByIdService(Database db) {
        this.db = db;
    }

    public void getById(Long id) {
        db.getById(id);
    }
}
