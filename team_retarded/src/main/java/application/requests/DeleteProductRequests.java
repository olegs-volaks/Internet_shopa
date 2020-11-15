package application.requests;

public class DeleteProductRequests {

    private final Long productId;

    public DeleteProductRequests(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}
