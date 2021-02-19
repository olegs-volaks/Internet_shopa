package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.requests.cart.DeleteCartRequest;
import eu.retarded.internetstore.core.responses.cart.DeleteCartResponse;
import eu.retarded.internetstore.database.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class DeleteCartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public DeleteCartResponse execute(DeleteCartRequest request) {
        Set<ConstraintViolation<DeleteCartRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteCartResponse(errors);
        }

        cartRepository.deleteById(request.getDeleteCartId());
        return new DeleteCartResponse(!cartRepository.existsById(request.getDeleteCartId()));
    }
}
