package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.requests.cart.AddCartRequest;
import eu.retarded.internetstore.core.responses.cart.AddCartResponse;
import eu.retarded.internetstore.database.CartRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class AddCartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Validator validator;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public AddCartResponse execute(AddCartRequest request) {
        Set<ConstraintViolation<AddCartRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddCartResponse(errors);
        }
        Cart cart = new Cart();
        cart.setUser(userRepository.getOne(request.getUserId()));
        return new AddCartResponse(cartRepository.save(cart));
    }
}
