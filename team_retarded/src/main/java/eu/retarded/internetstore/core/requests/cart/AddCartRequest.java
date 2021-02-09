package eu.retarded.internetstore.core.requests.cart;

public class AddCartRequest {

    private final Long userId;

    public AddCartRequest(Long userId) {

        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

}
