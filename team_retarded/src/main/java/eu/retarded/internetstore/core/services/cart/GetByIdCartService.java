package eu.retarded.internetstore.core.services.cart;

import eu.retarded.internetstore.core.requests.cart.GetByIdCartRequest;
import eu.retarded.internetstore.core.responses.cart.GetByIdCartResponse;
import eu.retarded.internetstore.database.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class GetByIdCartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public GetByIdCartResponse execute(GetByIdCartRequest request) {
        Set<ConstraintViolation<GetByIdCartRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetByIdCartResponse(errors);
        }

        return new GetByIdCartResponse(cartRepository.getOne(request.getId()));
    }
}
