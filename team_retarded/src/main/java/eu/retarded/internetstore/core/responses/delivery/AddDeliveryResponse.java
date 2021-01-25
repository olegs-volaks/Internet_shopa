package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;


public class AddDeliveryResponse extends CoreResponse {

    private long deliveryId;

    public AddDeliveryResponse(long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public AddDeliveryResponse(List<CoreError> errors) {
        super(errors);
    }

    public long getDeliveryId() {
        return deliveryId;
    }
}
