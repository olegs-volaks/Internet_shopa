package application.core.services.product;

import application.core.responses.product.ClearAllProductsResponse;
import application.database.ProductDatabase;

public class ClearAllProductsService {
    private final ProductDatabase db;

    public ClearAllProductsService(ProductDatabase db) {
        this.db = db;

    }
    public ClearAllProductsResponse execute() {
        db.clear();
        return new ClearAllProductsResponse();
    }
}

