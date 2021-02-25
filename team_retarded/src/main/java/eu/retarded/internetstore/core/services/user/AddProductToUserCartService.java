package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.AddProductToUserCartRequest;
import eu.retarded.internetstore.core.responses.user.AddProductToUserCartResponse;
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
public class AddProductToUserCartService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public AddProductToUserCartResponse execute(AddProductToUserCartRequest request) {
            Set<ConstraintViolation<AddProductToUserCartRequest>> errors = validator.validate(request);
            if (!errors.isEmpty()) {
                return new AddProductToUserCartResponse(errors);
            }
        User activeUser = userRepository.getOne(request.getUserId());
        Cart cart = cartRepository.getOne(activeUser.getCart().getId());
        cart.getProducts().put(productRepository.getOne(request.getProductId()), request.getCount());
       // productRepository
                //.getOne(request.getProductId())
                //.setCount(productRepository.getOne(request.getProductId()).getCount()- request.getCount());
        return new AddProductToUserCartResponse(cart);
    }
}
