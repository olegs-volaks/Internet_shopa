package eu.retarded.internetstore.core.requests.product;

public class DeleteProductRequest {

    private final long productId;

    public DeleteProductRequest(long productId) {
        this.productId = productId;
    }

    public long getProductId() {
        return productId;
    }
}
