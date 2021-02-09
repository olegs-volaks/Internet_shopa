package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;


public class AddOrderResponse extends CoreResponse {

    private Long orderId;

    public AddOrderResponse(Long orderId) {
        this.orderId = orderId;
    }

    public AddOrderResponse(List<CoreError> errors) {
        super(errors);
    }

    public Long getOrderId() {
        return orderId;
    }
}
