package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class AddProductResponse extends CoreResponse {

    private long productId;

    public AddProductResponse(long productId) {
        this.productId = productId;
    }

    public AddProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public long getProductId() {
        return productId;
    }
}
