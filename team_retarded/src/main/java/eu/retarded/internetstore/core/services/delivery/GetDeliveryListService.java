package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.requests.delivery.GetDeliveryListRequest;
import eu.retarded.internetstore.core.responses.delivery.GetDeliveryListResponse;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class GetDeliveryListService {

    @Autowired
    DeliveryDatabase deliveryDatabase ;

    @Transactional
    public GetDeliveryListResponse execute(GetDeliveryListRequest request) {
        return new GetDeliveryListResponse(null,deliveryDatabase.getList());
    }
}
