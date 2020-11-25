package application.core.services;

import application.core.requests.ShowAllProductsRequest;
import application.core.responses.ShowAllProductsResponse;
import application.database.ProductDatabase;

public class ShowAllProductsService {
    private final ProductDatabase db;

    public ShowAllProductsService(ProductDatabase db) {
        this.db = db;
    }

    public ShowAllProductsResponse execute(ShowAllProductsRequest request) {
        return new ShowAllProductsResponse(db.getList());
    }
}
