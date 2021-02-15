package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.UpdateDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateDeliveryResponse extends CoreResponse<UpdateDeliveryRequest> {

    private Delivery deliveryId;

    public Delivery getDeliveryId() {
        return deliveryId;
    }

    public UpdateDeliveryResponse(Delivery deliveryId) {
        this.deliveryId = deliveryId;
    }

    public UpdateDeliveryResponse(Set<ConstraintViolation<UpdateDeliveryRequest>> errors) {
        super(errors);
    }
}

