package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.requests.order.GetByIdOrderRequest;
import eu.retarded.internetstore.core.responses.order.GetByIdOrderResponse;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class GetByIdOrderService {

    @Autowired
    private OrderDatabase orderDatabase;

    @Autowired
    private Validator validator;

    public GetByIdOrderResponse execute(GetByIdOrderRequest request) {
        Set<ConstraintViolation<GetByIdOrderRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetByIdOrderResponse(errors);
        }
        return new GetByIdOrderResponse(orderDatabase.getById(request.getId()).get());
    }
}
