package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.UpdateOrderRequest;
import eu.retarded.internetstore.core.responses.order.UpdateOrderResponse;
import eu.retarded.internetstore.database.order.OrderDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

@Component
public class UpdateOrderService {

    @Autowired
    private OrderDatabase orderDatabase;

    @Autowired
    private Validator validator;


    public UpdateOrderResponse execute(UpdateOrderRequest request) {
        Set<ConstraintViolation<UpdateOrderRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateOrderResponse(errors);
        }

        Order resultOrder = new Order();
        resultOrder.setName(request.getName());
        resultOrder.setSurname(request.getSurname());
        resultOrder.setTotalPrice(BigDecimal.valueOf(request.getTotalPrice()));
        orderDatabase.updateOrder(resultOrder);
        return new UpdateOrderResponse(request.getId());
    }
}
