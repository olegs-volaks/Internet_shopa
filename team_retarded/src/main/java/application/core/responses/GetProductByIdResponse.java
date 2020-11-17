package application.core.responses;

import application.items.Product;

import java.util.List;
import java.util.Optional;

public class GetProductByIdResponse extends CoreResponse {

    private Optional<Product> productId;

    public GetProductByIdResponse(Optional<Product> productId) {
        this.productId = productId;
    }

    public GetProductByIdResponse(List<CoreError> errors) {
        super(errors);
    }

    public Optional<Product> getProductId() {
        return productId;
    }
}
