package application.core.services;

import application.core.requests.ShowAllProductsRequest;
import application.core.responses.ShowAllProductsResponse;
import application.database.Database;

public class ShowAllProductsService {
    private final Database db;

    public ShowAllProductsService(Database db) {
        this.db = db;
    }

    public ShowAllProductsResponse execute(ShowAllProductsRequest request) {
        return new ShowAllProductsResponse(db.getList());
    }
}
