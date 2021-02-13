package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.GetByIdDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.Set;


public class GetByIdDeliveryResponse extends CoreResponse<GetByIdDeliveryRequest> {

    private Delivery delivery;

    public GetByIdDeliveryResponse(Delivery delivery) {
        this.delivery = delivery;
    }

    public GetByIdDeliveryResponse(Set<ConstraintViolation<GetByIdDeliveryRequest>> errors) {
        super(errors);
    }

    public Delivery getDelivery() {
        return delivery;
    }
}

