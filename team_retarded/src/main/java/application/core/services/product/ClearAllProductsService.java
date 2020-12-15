package application.core.services.product;

import application.core.responses.product.ClearAllProductsResponse;
import application.database.ProductDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;


@Component
public class ClearAllProductsService {
    @Autowired
    private ProductDatabase db;

    public ClearAllProductsResponse execute() {
        db.clear();
        return new ClearAllProductsResponse();
    }
}

