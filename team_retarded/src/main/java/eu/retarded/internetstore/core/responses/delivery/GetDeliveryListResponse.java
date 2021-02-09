package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.CoreResponse;

import java.util.List;

public class GetDeliveryListResponse extends CoreResponse {

    private final List<Delivery> deliveries;

    public GetDeliveryListResponse(List<CoreError> errors, List<Delivery> deliveries) {
        super(errors);
        this.deliveries = deliveries;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }
}
