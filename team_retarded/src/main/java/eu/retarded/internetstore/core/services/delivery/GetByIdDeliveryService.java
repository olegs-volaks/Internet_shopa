package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.requests.delivery.GetByIdDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.GetByIdDeliveryResponse;
import eu.retarded.internetstore.database.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class GetByIdDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public GetByIdDeliveryResponse execute(GetByIdDeliveryRequest request) {
        Set<ConstraintViolation<GetByIdDeliveryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetByIdDeliveryResponse(errors);
        }
        return new GetByIdDeliveryResponse(deliveryRepository.findById(request.getId()).get());
    }
}
