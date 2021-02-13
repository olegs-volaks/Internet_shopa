package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.requests.order.AddOrderRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;


public class AddOrderResponse extends CoreResponse<AddOrderRequest> {

    private Long orderId;

    public AddOrderResponse(Long orderId) {
        this.orderId = orderId;
    }

    public AddOrderResponse(Set<ConstraintViolation<AddOrderRequest>> errors) {
        super(errors);
    }

    public Long getOrderId() {
        return orderId;
    }
}
