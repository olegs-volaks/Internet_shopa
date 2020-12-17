package eu.retarded.internetstore.core.responses.product;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

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
