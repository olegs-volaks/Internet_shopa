package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.cart.UpdateCartRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.cart.UpdateCartResponse;
import eu.retarded.internetstore.core.services.validators.cart.UpdateCartValidator;
import eu.retarded.internetstore.database.cart.CartDatabase;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateCartService {

    @Autowired CartDatabase cartDatabase;

    @Autowired UpdateCartValidator validator;

    @Autowired UsersDatabase usersDatabase;

    public UpdateCartResponse execute(UpdateCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateCartResponse(errors);
        }
        long id = request.getId();
        Cart result = new Cart();
        result.setId(id);
        result.setUser(usersDatabase.getUserById(request.getUser_id()).get());
        result.setStatus(request.getStatus());
        cartDatabase.updateCart(result);
        return new UpdateCartResponse(id);
    }
}
