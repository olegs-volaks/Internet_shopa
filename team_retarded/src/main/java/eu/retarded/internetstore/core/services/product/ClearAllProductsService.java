package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.responses.product.ClearAllProductsResponse;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class ClearAllProductsService {
    @Autowired
    private ProductDatabase db;

    @Transactional
    public ClearAllProductsResponse execute() {
        db.clear();
        return new ClearAllProductsResponse();
    }
}

