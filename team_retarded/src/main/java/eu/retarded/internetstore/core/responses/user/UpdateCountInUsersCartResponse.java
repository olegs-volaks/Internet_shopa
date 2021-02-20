package eu.retarded.internetstore.core.responses.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.user.UpdateCountInUsersCartRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateCountInUsersCartResponse extends CoreResponse<UpdateCountInUsersCartRequest> {

    private Cart cart;

    public UpdateCountInUsersCartResponse(Set<ConstraintViolation<UpdateCountInUsersCartRequest>> errors) {
        super(errors);
    }

    public UpdateCountInUsersCartResponse(Cart cart) {
        this.cart = cart;
    }

    public Cart getCart() {
        return cart;
    }
}
