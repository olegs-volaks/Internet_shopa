package eu.retarded.internetstore.core.requests.product;

public class DeleteProductFromCategoryRequest {
    private final long productId;

    public long DeleteProductFromCategoryProductId() {
        return productId;
    }

    public DeleteProductFromCategoryRequest(long productId) {
        this.productId = productId;
    }
}
