package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.AddDeliveryResponse;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class AddDeliveryService {

    @Autowired
    private DeliveryDatabase deliveryDatabase;

    @Autowired
    private Validator validator;

    public AddDeliveryResponse execute(AddDeliveryRequest request) {
        Set<ConstraintViolation<AddDeliveryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddDeliveryResponse(errors);
        }
        return new AddDeliveryResponse(deliveryDatabase.add(new Delivery(request.getTitle(), request.getRegion(), request.getPrice())));
    }
}
