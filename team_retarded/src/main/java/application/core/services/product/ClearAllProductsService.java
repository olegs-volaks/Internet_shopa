package application.core.services.product;

import application.core.responses.product.ClearAllProductsResponse;
import application.database.ProductDatabase;
import di.DIComponent;
import di.DIDependency;

@DIComponent
public class ClearAllProductsService {
    @DIDependency
    private ProductDatabase db;

    public ClearAllProductsResponse execute() {
        db.clear();
        return new ClearAllProductsResponse();
    }
}

