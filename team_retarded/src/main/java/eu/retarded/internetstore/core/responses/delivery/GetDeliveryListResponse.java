package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.GetDeliveryListRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class GetDeliveryListResponse extends CoreResponse<GetDeliveryListRequest> {

    private final List<Delivery> deliveries;

    public GetDeliveryListResponse(Set<ConstraintViolation<GetDeliveryListRequest>> errors, List<Delivery> deliveries) {
        super(errors);
        this.deliveries = deliveries;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }
}
