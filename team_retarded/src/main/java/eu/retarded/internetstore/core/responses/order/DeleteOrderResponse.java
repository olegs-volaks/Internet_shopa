package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.requests.order.DeleteOrderRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class DeleteOrderResponse extends CoreResponse<DeleteOrderRequest> {

    private boolean isOrderDeleted;

    public DeleteOrderResponse(boolean isDeliveryDeleted) {
        this.isOrderDeleted = isDeliveryDeleted;
    }

    public DeleteOrderResponse(Set<ConstraintViolation<DeleteOrderRequest>> errors) {
        super(errors);
    }

    public boolean isOrderDeleted() {
        return isOrderDeleted;
    }
}
