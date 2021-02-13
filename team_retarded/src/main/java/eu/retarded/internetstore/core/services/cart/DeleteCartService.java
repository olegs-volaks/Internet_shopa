package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.requests.cart.DeleteCartRequest;
import eu.retarded.internetstore.core.responses.cart.DeleteCartResponse;
import eu.retarded.internetstore.database.cart.CartDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class DeleteCartService {

    @Autowired
    private CartDatabase cartDatabase;

    @Autowired
    private Validator validator;


    public DeleteCartResponse execute(DeleteCartRequest request) {
        Set<ConstraintViolation<DeleteCartRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteCartResponse(errors);
        }

        return new DeleteCartResponse(cartDatabase.delete(request.getDeleteCartId()));
    }
}
