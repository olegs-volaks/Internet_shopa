package eu.retarded.internetstore.core.responses.basket;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class AddProductToBasketResponse extends CoreResponse {

    private boolean productInBasket;

    public AddProductToBasketResponse(boolean productInBasket) {
        this.productInBasket = productInBasket;
    }

    public AddProductToBasketResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean productInBasket() {
        return productInBasket;
    }
}