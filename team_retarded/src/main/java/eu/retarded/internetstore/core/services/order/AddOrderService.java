package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.AddOrderRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.order.AddOrderResponse;
import eu.retarded.internetstore.core.services.validators.order.AddOrderValidator;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddOrderService {

    @Autowired
    private OrderDatabase orderDatabase;
    @Autowired
    private AddOrderValidator validator;

    public AddOrderResponse execute(AddOrderRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddOrderResponse(errors);
        }
        return new AddOrderResponse(orderDatabase.add(new Order()));
    }
}
