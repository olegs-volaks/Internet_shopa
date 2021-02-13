package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.requests.delivery.GetByIdDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.GetByIdDeliveryResponse;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class GetByIdDeliveryService {

    @Autowired
    private DeliveryDatabase deliveryDatabase;

    @Autowired
    private Validator validator;

    public GetByIdDeliveryResponse execute(GetByIdDeliveryRequest request) {
        Set<ConstraintViolation<GetByIdDeliveryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetByIdDeliveryResponse(errors);
        }
        return new GetByIdDeliveryResponse(deliveryDatabase.getById(request.getId()).get());
    }
}
