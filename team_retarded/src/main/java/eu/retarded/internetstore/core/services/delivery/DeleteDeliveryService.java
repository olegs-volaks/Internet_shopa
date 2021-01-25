package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.requests.delivery.DeleteDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.delivery.DeleteDeliveryResponse;
import eu.retarded.internetstore.core.services.validators.delivery.DeleteDeliveryValidator;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteDeliveryService {


    @Autowired
    DeliveryDatabase deliveryDatabase;
    @Autowired
    DeleteDeliveryValidator validator;

    public DeleteDeliveryResponse execute(DeleteDeliveryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteDeliveryResponse(errors);
        }
        return new DeleteDeliveryResponse(deliveryDatabase.delete(request.getDeliveryIdToDelete()));
    }
}
