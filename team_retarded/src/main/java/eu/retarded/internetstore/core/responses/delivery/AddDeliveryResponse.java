package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;


public class AddDeliveryResponse extends CoreResponse<AddDeliveryRequest> {

    private Delivery deliveryId;

    public AddDeliveryResponse(Delivery deliveryId) {
        this.deliveryId = deliveryId;
    }

    public AddDeliveryResponse(Set<ConstraintViolation<AddDeliveryRequest>> errors) {
        super(errors);
    }

    public Delivery getDeliveryId() {
        return deliveryId;
    }
}
