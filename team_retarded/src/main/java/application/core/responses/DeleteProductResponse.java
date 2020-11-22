package application.core.responses;

import java.util.List;

public class DeleteProductResponse extends CoreResponse {

    private boolean isProductDeleted;

    public DeleteProductResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteProductResponse(boolean isProductDeleted) {
        this.isProductDeleted = isProductDeleted;
    }

    public boolean idProductDeleted() {
        return isProductDeleted;
    }
}
