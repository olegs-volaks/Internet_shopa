package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;


public class GetByIdOrderResponse extends CoreResponse {

    private Order order;

    public GetByIdOrderResponse(Order order) {
        this.order = order;
    }

    public GetByIdOrderResponse(List<CoreError> errors) {
        super(errors);
    }

    public Order getOrder() {
        return order;
    }
}

