package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;


public class GetByIdDeliveryResponse  extends CoreResponse {

    private Delivery  delivery;

    public GetByIdDeliveryResponse(Delivery delivery) {
        this.delivery = delivery;
    }

    public GetByIdDeliveryResponse(List<CoreError> errors) {
        super(errors);
    }

    public Delivery getDelivery() {
        return delivery;
    }
}

