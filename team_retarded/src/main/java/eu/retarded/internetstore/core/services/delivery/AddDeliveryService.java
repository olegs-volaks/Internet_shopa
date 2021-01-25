package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.delivery.AddDeliveryResponse;
import eu.retarded.internetstore.core.services.validators.delivery.AddDeliveryValidator;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddDeliveryService {

    @Autowired
    private DeliveryDatabase deliveryDatabase;
    @Autowired
    private AddDeliveryValidator validator;

    public AddDeliveryResponse execute(AddDeliveryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddDeliveryResponse(errors);
        }
        return new AddDeliveryResponse(deliveryDatabase.add(new Delivery(request.getTitle(), request.getRegion(), request.getPrice())));
    }
}
