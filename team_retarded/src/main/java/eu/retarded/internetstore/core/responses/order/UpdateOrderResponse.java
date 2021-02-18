package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.UpdateOrderRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateOrderResponse extends CoreResponse<UpdateOrderRequest> {

    private Order order;

    public Order getOrder() {
        return order;
    }

    public UpdateOrderResponse(Order order) {
        this.order = order;
    }

    public UpdateOrderResponse(Set<ConstraintViolation<UpdateOrderRequest>> errors) {
        super(errors);
    }
}

