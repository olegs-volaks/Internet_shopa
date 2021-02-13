package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.requests.order.DeleteOrderRequest;
import eu.retarded.internetstore.core.responses.order.DeleteOrderResponse;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class DeleteOrderService {

    @Autowired
    private OrderDatabase orderDatabase;

    @Autowired
    private Validator validator;

    public DeleteOrderResponse execute(DeleteOrderRequest request) {
        Set<ConstraintViolation<DeleteOrderRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteOrderResponse(errors);
        }
        return new DeleteOrderResponse(orderDatabase.delete(request.getDeleteOrderId()));
    }
}
