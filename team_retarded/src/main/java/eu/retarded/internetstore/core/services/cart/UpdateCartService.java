package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.cart.UpdateCartRequest;
import eu.retarded.internetstore.core.responses.cart.UpdateCartResponse;
import eu.retarded.internetstore.database.CartRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class UpdateCartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Validator validator;

    @Autowired
    private UserRepository userRepository;

    public UpdateCartResponse execute(UpdateCartRequest request) {
        Set<ConstraintViolation<UpdateCartRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateCartResponse(errors);
        }

        long id = request.getId();
        Cart oldCart = cartRepository.findById(id).get();
        Cart result = new Cart();
        result.setId(id);
        result.setUser(userRepository.getOne(request.getUser_id()));
        result.setStatus(oldCart.getStatus());
        return new UpdateCartResponse(cartRepository.save(result));
    }
}
