package eu.retarded.internetstore.core.services.cart;


import eu.retarded.internetstore.core.requests.cart.GetByIdCartRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.cart.GetByIdCartResponse;
import eu.retarded.internetstore.core.services.validators.cart.GetByIdCartValidator;
import eu.retarded.internetstore.database.cart.CartDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetByIdCartService {

    @Autowired CartDatabase cartDatabase;

    @Autowired GetByIdCartValidator validator;


    public GetByIdCartResponse execute(GetByIdCartRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetByIdCartResponse(errors);
        }

        return new GetByIdCartResponse(cartDatabase.getById(request.getId()).get());
    }
}
