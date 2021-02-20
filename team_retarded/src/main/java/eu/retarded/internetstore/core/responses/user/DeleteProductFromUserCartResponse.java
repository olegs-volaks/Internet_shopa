package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.user.DeleteProductFromUserCartRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class DeleteProductFromUserCartResponse  extends CoreResponse<DeleteProductFromUserCartRequest> {

    private Cart cart;

    public DeleteProductFromUserCartResponse(Set<ConstraintViolation<DeleteProductFromUserCartRequest>> errors) {
        super(errors);
    }

    public DeleteProductFromUserCartResponse(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }
}
