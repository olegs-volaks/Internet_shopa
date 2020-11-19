package application.core.responses;

import java.util.List;

public class DeleteProductResponse extends CoreResponse {

    private boolean productRemoved;

    public DeleteProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteProductResponse(boolean productRemoved) {
        this.productRemoved = productRemoved;
    }

    public boolean isProductRemoved() {
        return productRemoved;
    }
}
