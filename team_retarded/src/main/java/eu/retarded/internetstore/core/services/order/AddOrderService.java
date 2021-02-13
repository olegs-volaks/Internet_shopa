package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.AddOrderRequest;
import eu.retarded.internetstore.core.responses.order.AddOrderResponse;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class AddOrderService {

    @Autowired
    private OrderDatabase orderDatabase;

    @Autowired
    private Validator validator;

    public AddOrderResponse execute(AddOrderRequest request) {
        Set<ConstraintViolation<AddOrderRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddOrderResponse(errors);
        }
        return new AddOrderResponse(orderDatabase.add(new Order()));
    }
}
