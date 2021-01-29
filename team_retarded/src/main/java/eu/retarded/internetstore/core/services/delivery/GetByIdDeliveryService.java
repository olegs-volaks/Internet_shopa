package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.requests.delivery.GetByIdDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.delivery.GetByIdDeliveryResponse;
import eu.retarded.internetstore.core.services.validators.delivery.GetByIdDeliveryValidator;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetByIdDeliveryService {

    @Autowired DeliveryDatabase deliveryDatabase;

    @Autowired GetByIdDeliveryValidator validator;

    public GetByIdDeliveryResponse execute(GetByIdDeliveryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetByIdDeliveryResponse(errors);
        }
        return new GetByIdDeliveryResponse(deliveryDatabase.getById(request.getId()));
    }
}
