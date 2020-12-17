package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.responses.product.ShowAllProductsResponse;
import eu.retarded.internetstore.database.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ShowAllProductsService {
    @Autowired
    private ProductDatabase db;

    public ShowAllProductsResponse execute(ShowAllProductsRequest request) {
        return new ShowAllProductsResponse(db.getList());
    }
}
