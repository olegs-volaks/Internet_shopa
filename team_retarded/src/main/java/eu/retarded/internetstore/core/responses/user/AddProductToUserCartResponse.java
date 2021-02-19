package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.user.AddProductToUserCartRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class AddProductToUserCartResponse extends CoreResponse<AddProductToUserCartRequest> {

    private Cart cart;

    public AddProductToUserCartResponse(Set<ConstraintViolation<AddProductToUserCartRequest>> errors) {
        super(errors);
    }

    public AddProductToUserCartResponse(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }
}
