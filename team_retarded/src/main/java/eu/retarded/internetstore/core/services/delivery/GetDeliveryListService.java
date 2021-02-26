package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.GetDeliveryListRequest;
import eu.retarded.internetstore.core.responses.delivery.GetDeliveryListResponse;
import eu.retarded.internetstore.database.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class GetDeliveryListService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    Validator validator;

    @Transactional
    public GetDeliveryListResponse execute(GetDeliveryListRequest request) {
        Set<ConstraintViolation<GetDeliveryListRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetDeliveryListResponse(errors);
        }
        List<Delivery> deliveries;
        if (request.getPageable() == null) {
            deliveries = deliveryRepository.findAll();
            return new GetDeliveryListResponse(null, deliveries);
        }
        Page<Delivery> deliveryPage = deliveryRepository.findAll(request.getPageable());
        return new GetDeliveryListResponse(deliveryPage, deliveryPage.toList());
    }
}
