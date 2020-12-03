package application.core.responses.product;

import application.core.responses.CoreError;
import application.core.responses.CoreResponse;

import java.util.List;

public class DeleteProductResponse extends CoreResponse {

    private boolean isProductDeleted;

    public DeleteProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteProductResponse(boolean isProductDeleted) {
        this.isProductDeleted = isProductDeleted;
    }

    public boolean isProductDeleted() {
        return isProductDeleted;
    }
}
