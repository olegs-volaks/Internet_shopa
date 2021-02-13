package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.requests.order.UpdateOrderRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateOrderResponse extends CoreResponse<UpdateOrderRequest> {

    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public UpdateOrderResponse(Long orderId) {
        this.orderId = orderId;
    }

    public UpdateOrderResponse(Set<ConstraintViolation<UpdateOrderRequest>> errors) {
        super(errors);
    }
}

