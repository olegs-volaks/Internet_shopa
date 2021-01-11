package eu.retarded.internetstore.core.responses.basket;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class AddProductToBasketResponse extends CoreResponse {

    private long userId;

    public AddProductToBasketResponse(long userId) {
        this.userId = userId;
    }

    public AddProductToBasketResponse(List<CoreError> errors) {
        super(errors);
    }

    public long getUserId() {
        return userId;
    }
}