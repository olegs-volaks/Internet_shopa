package eu.retarded.internetstore.core.responses.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.cart.GetByIdCartRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class GetByIdCartResponse extends CoreResponse<GetByIdCartRequest> {

    private Cart cart;

    public GetByIdCartResponse(Cart cart) {
        this.cart = cart;
    }

    public GetByIdCartResponse(Set<ConstraintViolation<GetByIdCartRequest>> errors) {
        super(errors);
    }

    public Cart getCart() {
        return cart;
    }
}
