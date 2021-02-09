package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.UpdateOrderRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.order.UpdateOrderResponse;
import eu.retarded.internetstore.core.services.validators.order.UpdateOrderValidator;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateOrderService {

    @Autowired
    private OrderDatabase orderDatabase;
    @Autowired
    private UpdateOrderValidator validator;


    public UpdateOrderResponse execute(UpdateOrderRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateOrderResponse(errors);
        }
        long id = request.getId();
        Order resultOrder =  new Order();
        resultOrder.setId(id);
        resultOrder.setName(request.getName());
        resultOrder.setSurname(request.getSurname());
        resultOrder.setTotalPrice(request.getTotalPrice());
        orderDatabase.updateOrder(resultOrder);
        return new UpdateOrderResponse(id);
    }
}
