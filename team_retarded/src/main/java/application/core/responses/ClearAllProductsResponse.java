package application.core.responses;

import java.util.List;

public class ClearAllProductsResponse extends CoreResponse {

    private long productId;

    public ClearAllProductsResponse(long productId) {
        this.productId = productId;
    }

    public ClearAllProductsResponse(List<CoreError> errors) {
        super(errors);
    }

    public long getProductId() {
        return productId;
    }
}
