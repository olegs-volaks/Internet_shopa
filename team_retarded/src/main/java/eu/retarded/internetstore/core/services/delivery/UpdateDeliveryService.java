package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.UpdateDeliveryRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.delivery.UpdateDeliveryResponse;
import eu.retarded.internetstore.core.services.validators.delivery.UpdateDeliveryValidator;
import eu.retarded.internetstore.database.delivery.DeliveryDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class UpdateDeliveryService {

    @Autowired
    private DeliveryDatabase deliveryDatabase;
    @Autowired
    private UpdateDeliveryValidator validator;


    public UpdateDeliveryResponse execute(UpdateDeliveryRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateDeliveryResponse(errors);
        }
        long id = request.getId();
        Delivery resultDelivery =  new Delivery();
        resultDelivery.setId(id);
        resultDelivery.setTitle(request.getTitle());
        resultDelivery.setRegion(request.getRegion());
        resultDelivery.setPrice(BigDecimal.valueOf(request.getPrice()));
        deliveryDatabase.updateDelivery(resultDelivery);
        return new UpdateDeliveryResponse(id);
    }
}
