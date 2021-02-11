package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.requests.cart.DeleteCartRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.cart.DeleteCartResponse;
import eu.retarded.internetstore.core.services.validators.cart.DeleteCartValidator;
import eu.retarded.internetstore.database.cart.CartDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteCartService {

    @Autowired CartDatabase cartDatabase;
    @Autowired DeleteCartValidator validator;


    public DeleteCartResponse execute(DeleteCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteCartResponse(errors);
        }

        return new DeleteCartResponse(cartDatabase.delete(request.getDeleteCartId()));
    }
}
