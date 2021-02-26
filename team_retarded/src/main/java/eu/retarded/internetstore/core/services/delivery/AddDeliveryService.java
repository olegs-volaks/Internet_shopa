package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.AddDeliveryResponse;
import eu.retarded.internetstore.database.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class AddDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public AddDeliveryResponse execute(AddDeliveryRequest request) {
        Set<ConstraintViolation<AddDeliveryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddDeliveryResponse(errors);
        }
        return new AddDeliveryResponse(deliveryRepository.save(new Delivery(request.getTitle(), request.getRegion(), request.getPrice())));
    }
}
