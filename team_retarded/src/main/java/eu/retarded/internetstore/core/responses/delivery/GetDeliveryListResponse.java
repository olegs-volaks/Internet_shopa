package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.GetDeliveryListRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class GetDeliveryListResponse extends CoreResponse<GetDeliveryListRequest> {

    private final Page <Delivery> deliveries;

    public GetDeliveryListResponse(Set<ConstraintViolation<GetDeliveryListRequest>> errors, Page <Delivery> deliveries) {
        super(errors);
        this.deliveries = deliveries;
    }

    public Page <Delivery> getDeliveries() {
        return deliveries;
    }
}
