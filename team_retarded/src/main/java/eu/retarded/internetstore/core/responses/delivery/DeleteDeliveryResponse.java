package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class DeleteDeliveryResponse extends CoreResponse {

    private boolean isDeliveryDeleted;

    public DeleteDeliveryResponse(boolean isDeliveryDeleted) {
        this.isDeliveryDeleted = isDeliveryDeleted;
    }

    public DeleteDeliveryResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isDeliveryDeleted() {
        return isDeliveryDeleted;
    }
}
