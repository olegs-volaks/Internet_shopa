package eu.retarded.internetstore.core.requests.basket;

public class AddProductToBasketRequest {


    private final Long userId;
    private final Long productId;
    private final Integer quantity;


    public Long getProductId() {
        return productId;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getQuantity() {
        return quantity;
    }


    public AddProductToBasketRequest(Long userId, Long productId, Integer quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;

    }
}

