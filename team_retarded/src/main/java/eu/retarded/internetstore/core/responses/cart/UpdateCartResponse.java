package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.cart.UpdateCartRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateCartResponse extends CoreResponse<UpdateCartRequest> {

    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public UpdateCartResponse(Cart cart) {
        this.cart = cart;
    }

    public UpdateCartResponse(Set<ConstraintViolation<UpdateCartRequest>> errors) {
        super(errors);
    }
}
