package application.responses;

import java.util.List;

public class DeleteProductResponse extends CoreResponse {

    private final boolean deleteProductId;

    public DeleteProductResponse(boolean deleteProductId) {
        this.deleteProductId = deleteProductId;
    }

    public DeleteProductResponse(List<CoreError> errors, boolean deleteProductId) {
        super(errors);
        this.deleteProductId = deleteProductId;
    }


    public boolean isDeleteProductId() {
        return deleteProductId;
    }
}
