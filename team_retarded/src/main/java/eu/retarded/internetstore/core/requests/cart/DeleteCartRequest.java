package eu.retarded.internetstore.core.requests.cart;

public class DeleteCartRequest {

    private final Long deleteCartId;

    public DeleteCartRequest(Long deleteCartId) {
        this.deleteCartId = deleteCartId;
    }

    public Long getDeleteCartId() {
        return deleteCartId;
    }
}
