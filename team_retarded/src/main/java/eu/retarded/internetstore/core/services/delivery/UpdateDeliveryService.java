package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.requests.delivery.UpdateDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.UpdateDeliveryResponse;
import eu.retarded.internetstore.database.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

@Service
public class UpdateDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public UpdateDeliveryResponse execute(UpdateDeliveryRequest request) {
        Set<ConstraintViolation<UpdateDeliveryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateDeliveryResponse(errors);
        }

        Delivery resultDelivery=deliveryRepository.getOne(request.getId());
        resultDelivery.setTitle(request.getTitle());
        resultDelivery.setRegion(request.getRegion());
        resultDelivery.setPrice(BigDecimal.valueOf(request.getPrice()));
        return new UpdateDeliveryResponse(resultDelivery);
    }
}
