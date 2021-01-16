package eu.retarded.internetstore.core.responses.basket;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class DeleteProductFromBasketResponse extends CoreResponse {
    private boolean productNotInBasket;

    public DeleteProductFromBasketResponse(boolean productNotInBasket) {
        this.productNotInBasket = productNotInBasket;
    }

    public DeleteProductFromBasketResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean productNotInBasket() {
        return productNotInBasket;
    }
}