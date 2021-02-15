package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.cart.AddCartRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class AddCartResponse extends CoreResponse<AddCartRequest> {

    private Cart cart;

    public AddCartResponse(Cart cart) {
        this.cart = cart;
    }

    public AddCartResponse(Set<ConstraintViolation<AddCartRequest>> errors) {
        super(errors);
    }


    public Cart getCart() {
        return cart;
    }
}
