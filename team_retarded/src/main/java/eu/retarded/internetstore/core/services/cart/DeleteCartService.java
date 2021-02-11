package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.cart.DeleteCartRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.cart.DeleteCartResponse;
import eu.retarded.internetstore.core.services.validators.cart.DeleteCartValidator;
import eu.retarded.internetstore.database.cart.CartDatabase;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteCartService {

    @Autowired CartDatabase cartDatabase;
    @Autowired DeleteCartValidator validator;
    @Autowired UsersDatabase usersDatabase;

    public DeleteCartResponse execute(DeleteCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteCartResponse(errors);
        }
        Cart cart = new Cart();
        cart.setUser(usersDatabase.getUserById(request.getDeleteCartId()).get());
        return new DeleteCartResponse(cartDatabase.delete(request.getDeleteCartId()));
    }
}
