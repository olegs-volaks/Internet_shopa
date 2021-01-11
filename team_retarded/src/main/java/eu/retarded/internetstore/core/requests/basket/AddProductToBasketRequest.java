package eu.retarded.internetstore.core.requests.basket;

public class AddProductToBasketRequest {

    private final Long id;
    private final Integer quantity;


    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }


    public AddProductToBasketRequest(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;

    }
}

