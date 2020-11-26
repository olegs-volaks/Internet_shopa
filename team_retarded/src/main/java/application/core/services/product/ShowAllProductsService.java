package application.core.services.product;

import application.core.requests.product.ShowAllProductsRequest;
import application.core.responses.product.ShowAllProductsResponse;
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
