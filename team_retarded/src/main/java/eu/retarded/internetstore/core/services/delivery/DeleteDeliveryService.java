package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.requests.delivery.DeleteDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.DeleteDeliveryResponse;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class DeleteDeliveryService {

    @Autowired
    private DeliveryDatabase deliveryDatabase;

    @Autowired
    private Validator validator;

    public DeleteDeliveryResponse execute(DeleteDeliveryRequest request) {
        Set<ConstraintViolation<DeleteDeliveryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteDeliveryResponse(errors);
        }
        return new DeleteDeliveryResponse(deliveryDatabase.delete(request.getDeleteDeliveryId()));
    }
}
