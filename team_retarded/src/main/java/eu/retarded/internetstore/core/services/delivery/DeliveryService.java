package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.DeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.delivery.DeliveryResponse;
import eu.retarded.internetstore.core.services.validators.delivery.DeliveryValidator;
import eu.retarded.internetstore.database.DeliveryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryService {

    @Autowired
    private DeliveryDatabase deliveryDatabase;
    @Autowired
    private DeliveryValidator validator;

    public DeliveryResponse execute(DeliveryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeliveryResponse(errors);
        }
        return new DeliveryResponse(deliveryDatabase.add(new Delivery(request.getTitle(),request.getRegion(),request.getPrice())));
    }
}
