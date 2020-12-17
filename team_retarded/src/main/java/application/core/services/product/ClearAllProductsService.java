package application.core.services.product;

import application.core.responses.product.ClearAllProductsResponse;
import application.database.ProductDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ClearAllProductsService {

    @Autowired private ProductDatabase database;

    public ClearAllProductsResponse execute() {
        database.clear();
        return new ClearAllProductsResponse();
    }
}

