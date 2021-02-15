package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.requests.delivery.GetDeliveryListRequest;
import eu.retarded.internetstore.core.responses.delivery.GetDeliveryListResponse;
import eu.retarded.internetstore.database.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class GetDeliveryListService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Transactional
    public GetDeliveryListResponse execute(GetDeliveryListRequest request) {
        return new GetDeliveryListResponse(null, deliveryRepository.findAll());
    }
}
