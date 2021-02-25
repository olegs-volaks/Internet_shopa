package eu.retarded.internetstore.core.responses.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.GetDeliveryListRequest;
import eu.retarded.internetstore.core.responses.CoreResponse;
import org.springframework.data.domain.Page;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

public class GetDeliveryListResponse extends CoreResponse<GetDeliveryListRequest> {

    private Page<Delivery> deliveriesPage;
    private List<Delivery> deliveriesList;

    public GetDeliveryListResponse(Set<ConstraintViolation<GetDeliveryListRequest>> errors) {
        super(errors);
    }

    public GetDeliveryListResponse(Page<Delivery> deliveriesPage, List<Delivery> deliveriesList) {
        this.deliveriesPage = deliveriesPage;
        this.deliveriesList = deliveriesList;
    }

    public Page<Delivery> getDeliveriesPage() {
        return deliveriesPage;
    }

    public List<Delivery> getDeliveriesList() {
        return deliveriesList;
    }
}
