package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.cart.UpdateCartRequest;
import eu.retarded.internetstore.core.responses.cart.UpdateCartResponse;
import eu.retarded.internetstore.database.cart.CartDatabase;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class UpdateCartService {

    @Autowired
    private CartDatabase cartDatabase;

    @Autowired
    private Validator validator;

    @Autowired
    private UsersDatabase usersDatabase;

    public UpdateCartResponse execute(UpdateCartRequest request) {
        Set<ConstraintViolation<UpdateCartRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateCartResponse(errors);
        }
        long id = request.getId();
        Cart oldCart = cartDatabase.getById(id).get();
        Cart result = new Cart();
        result.setId(id);
        result.setUser(usersDatabase.getUserById(request.getUser_id()).get());
        result.setStatus(oldCart.getStatus());
        cartDatabase.updateCart(result);
        return new UpdateCartResponse(id);
    }
}
