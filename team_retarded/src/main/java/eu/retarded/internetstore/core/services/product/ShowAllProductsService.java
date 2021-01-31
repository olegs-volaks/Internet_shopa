package eu.retarded.internetstore.core.services.product;

import eu.retarded.internetstore.core.requests.product.ShowAllProductsRequest;
import eu.retarded.internetstore.core.responses.product.ShowAllProductsResponse;
import eu.retarded.internetstore.database.product.ProductDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class ShowAllProductsService {
    @Autowired
    private ProductDatabase db;

    @Transactional
    public ShowAllProductsResponse execute(ShowAllProductsRequest request) {
        return new ShowAllProductsResponse(db.getList());
    }
}
