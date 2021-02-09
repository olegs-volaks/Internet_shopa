package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.cart.AddCartRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.cart.AddCartResponse;
import eu.retarded.internetstore.core.services.validators.cart.AddCartValidator;
import eu.retarded.internetstore.database.cart.CartDatabase;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AddCartService {

    @Autowired private CartDatabase cartDatabase;
    @Autowired private AddCartValidator validator;
    @Autowired private UsersDatabase usersDatabase;

    @Transactional
    public AddCartResponse execute(AddCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddCartResponse(errors);
        }
        Cart cart = new Cart();
        cart.setUser(usersDatabase.getUserById(request.getUserId()).get());
        return new AddCartResponse(cartDatabase.add(cart));
    }
}
