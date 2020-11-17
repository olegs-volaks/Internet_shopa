package application.core.requests;

public class GetProductByIdRequest {

    private long productId;

    public GetProductByIdRequest(long productId) {
        this.productId = productId;
    }

    public long getProductId() {
        return productId;
    }
}
