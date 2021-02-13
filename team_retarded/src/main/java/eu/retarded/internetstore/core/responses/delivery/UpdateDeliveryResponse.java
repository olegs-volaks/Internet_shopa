package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.requests.delivery.UpdateDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class UpdateDeliveryResponse extends CoreResponse<UpdateDeliveryRequest> {

    private Long deliveryId;

    public Long getDeliveryId() {
        return deliveryId;
    }

    public UpdateDeliveryResponse(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public UpdateDeliveryResponse(Set<ConstraintViolation<UpdateDeliveryRequest>> errors) {
        super(errors);
    }
}

