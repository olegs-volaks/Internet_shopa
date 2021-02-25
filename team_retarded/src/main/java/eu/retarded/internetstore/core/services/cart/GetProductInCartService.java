package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.requests.cart.GetProductInCartRequest;
import eu.retarded.internetstore.core.responses.cart.GetProductInCartResponse;
import eu.retarded.internetstore.database.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class GetProductInCartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public GetProductInCartResponse execute(GetProductInCartRequest request) {
        Set<ConstraintViolation<GetProductInCartRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetProductInCartResponse(errors);
        }
        return new GetProductInCartResponse(cartRepository.getOne(request.getCartId()).getProducts());
    }
}
