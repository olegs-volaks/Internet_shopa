package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.requests.cart.UpdateCartRequest;
import eu.retarded.internetstore.core.responses.cart.UpdateCartResponse;
import eu.retarded.internetstore.database.CartRepository;
import eu.retarded.internetstore.database.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class UpdateCartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public UpdateCartResponse execute(UpdateCartRequest request) {
        Set<ConstraintViolation<UpdateCartRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateCartResponse(errors);
        }
        Product product = productRepository.getOne(request.getProductId());
        Cart cart = cartRepository.getOne(request.getId());
        cart.getProducts().replace(productRepository.getOne(request.getProductId()), request.getCount());
        return new UpdateCartResponse(cart);
    }
}
