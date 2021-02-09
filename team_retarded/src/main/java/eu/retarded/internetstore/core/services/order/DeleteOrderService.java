package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.requests.order.DeleteOrderRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.order.DeleteOrderResponse;
import eu.retarded.internetstore.core.services.validators.order.DeleteOrderValidator;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteOrderService {


    @Autowired
    OrderDatabase orderDatabase;
    @Autowired
    DeleteOrderValidator validator;

    public DeleteOrderResponse execute(DeleteOrderRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteOrderResponse(errors);
        }
        return new DeleteOrderResponse(orderDatabase.delete(request.getDeleteOrderId()));
    }
}
