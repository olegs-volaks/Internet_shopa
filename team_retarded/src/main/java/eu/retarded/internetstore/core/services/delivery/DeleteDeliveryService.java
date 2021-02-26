package eu.retarded.internetstore.core.services.delivery;

import eu.retarded.internetstore.core.requests.delivery.DeleteDeliveryRequest;
import eu.retarded.internetstore.core.responses.delivery.DeleteDeliveryResponse;
import eu.retarded.internetstore.database.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class DeleteDeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public DeleteDeliveryResponse execute(DeleteDeliveryRequest request) {
        Set<ConstraintViolation<DeleteDeliveryRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteDeliveryResponse(errors);
        }
        deliveryRepository.deleteById(request.getDeleteDeliveryId());
        return new DeleteDeliveryResponse(!deliveryRepository.existsById(request.getDeleteDeliveryId()));
    }
}
