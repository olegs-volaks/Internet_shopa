package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class GetByIdCartResponse extends CoreResponse {

    private Cart cart;

    public GetByIdCartResponse(Cart cart) {
        this.cart = cart;
    }

    public GetByIdCartResponse(List<CoreError> errors) {
        super(errors);
    }

    public Cart getCart() {
        return cart;
    }
}
