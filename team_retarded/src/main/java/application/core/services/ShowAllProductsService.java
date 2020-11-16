package application.core.services;

import application.bd.Database;
import application.core.requests.ShowAllProductsRequest;
import application.core.responses.ShowAllProductsResponse;

public class ShowAllProductsService {
    private final Database db;

    public ShowAllProductsService(Database db) {
        this.db = db;
    }

    public ShowAllProductsResponse execute(ShowAllProductsRequest request) {
        return new ShowAllProductsResponse(db.getList());
    }
}
