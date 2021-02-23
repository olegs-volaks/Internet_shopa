package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.UpdateOrderStatusRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateOrderStatusResponse extends CoreResponse<UpdateOrderStatusRequest> {

    private Order order;

    public Order getOrder() {
        return order;
    }

    public UpdateOrderStatusResponse(Order order) {
        this.order = order;
    }

    public UpdateOrderStatusResponse(Set<ConstraintViolation<UpdateOrderStatusRequest>> errors) {
        super(errors);
    }
}

