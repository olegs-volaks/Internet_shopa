package application.core.responses;

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
