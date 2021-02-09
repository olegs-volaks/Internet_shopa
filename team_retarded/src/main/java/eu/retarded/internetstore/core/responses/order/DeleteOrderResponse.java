package eu.retarded.internetstore.core.responses.order;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class DeleteOrderResponse extends CoreResponse {

    private boolean isOrderDeleted;

    public DeleteOrderResponse(boolean isDeliveryDeleted) {
        this.isOrderDeleted = isDeliveryDeleted;
    }

    public DeleteOrderResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isOrderDeleted() {
        return isOrderDeleted;
    }
}
