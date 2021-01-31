package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class UpdateProductResponse extends CoreResponse {
    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public UpdateProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public UpdateProductResponse(Long productId) {
        this.productId = productId;
    }
}
