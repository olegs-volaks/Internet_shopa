package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;


public class DeliveryResponse extends CoreResponse {

    private long deliveryId;

    public DeliveryResponse(long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public DeliveryResponse(List<CoreError> errors) {
        super(errors);

    }

    public long getDeliveryId() {
        return deliveryId;
    }
}
