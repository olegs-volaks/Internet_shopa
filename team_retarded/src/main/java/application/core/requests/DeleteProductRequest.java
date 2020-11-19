package application.core.requests;

public class DeleteProductRequest {

    private final long productIdToDelete;

    public DeleteProductRequest(long productIdToDelete) {
        this.productIdToDelete = productIdToDelete;
    }

    public long getProductIdToDelete() {
        return productIdToDelete;
    }
}
