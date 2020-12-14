package application.core.services.product;

import application.core.requests.product.ShowAllProductsRequest;
import application.core.responses.product.ShowAllProductsResponse;
import application.database.ProductDatabase;
import com.retarded.di.DIComponent;
import com.retarded.di.DIDependency;


@DIComponent
public class ShowAllProductsService {
    @DIDependency
    private ProductDatabase db;

    public ShowAllProductsResponse execute(ShowAllProductsRequest request) {
        return new ShowAllProductsResponse(db.getList());
    }
}
