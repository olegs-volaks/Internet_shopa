package application.core.responses;

import application.items.Product;

import java.util.List;
import java.util.Optional;

public class GetProductByIdResponse extends CoreResponse {

    private long productId;

    public GetProductByIdResponse(Optional<Product> productId) {
        this.productId = productId;
    }

    public GetProductByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public long getProductId() {
        return productId;
    }
}
