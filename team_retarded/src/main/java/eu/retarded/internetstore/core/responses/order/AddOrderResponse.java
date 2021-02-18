package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.AddOrderRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;


public class AddOrderResponse extends CoreResponse<AddOrderRequest> {

    private Order order;

    public AddOrderResponse(Order order) {
        this.order = order;
    }

    public AddOrderResponse(Set<ConstraintViolation<AddOrderRequest>> errors) {
        super(errors);
    }

    public Order getOrder() {
        return order;
    }
}
