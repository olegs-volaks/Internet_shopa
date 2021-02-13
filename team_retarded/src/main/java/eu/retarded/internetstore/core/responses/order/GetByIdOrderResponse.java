package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.GetByIdOrderRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;


public class GetByIdOrderResponse extends CoreResponse<GetByIdOrderRequest> {

    private Order order;

    public GetByIdOrderResponse(Order order) {
        this.order = order;
    }

    public GetByIdOrderResponse(Set<ConstraintViolation<GetByIdOrderRequest>> errors) {
        super(errors);
    }

    public Order getOrder() {
        return order;
    }
}

