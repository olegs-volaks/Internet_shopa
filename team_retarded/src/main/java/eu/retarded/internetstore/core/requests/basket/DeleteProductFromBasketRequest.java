package eu.retarded.internetstore.core.requests.basket;

public class DeleteProductFromBasketRequest {
    private final Long userId;
    private final Long productId;


    public Long getProductId() {
        return productId;
    }

    public Long getUserId() {
        return userId;
    }

    public DeleteProductFromBasketRequest(Long userId, Long productId) {
        this.userId = userId;
        this.productId = productId;
    }
}