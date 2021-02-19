package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.DeleteProductFromUserCartRequest;
import eu.retarded.internetstore.core.responses.user.DeleteProductFromUserCartResponse;
import eu.retarded.internetstore.database.CartRepository;
import eu.retarded.internetstore.database.ProductRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class DeleteProductFromUserCartService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public DeleteProductFromUserCartResponse execute(DeleteProductFromUserCartRequest request) {
        Set<ConstraintViolation<DeleteProductFromUserCartRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteProductFromUserCartResponse(errors);
        }
        User activeUser = userRepository.getOne(request.getUserId());
        Cart cart = cartRepository.getOne(activeUser.getCart().getId());
        cart.getProducts().remove(productRepository.getOne(request.getProductId()));
        return new DeleteProductFromUserCartResponse(cart);
    }
}
