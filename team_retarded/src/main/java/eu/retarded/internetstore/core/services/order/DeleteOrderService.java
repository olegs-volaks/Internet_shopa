package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.requests.order.DeleteOrderRequest;
import eu.retarded.internetstore.core.responses.order.DeleteOrderResponse;
import eu.retarded.internetstore.database.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class DeleteOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public DeleteOrderResponse execute(DeleteOrderRequest request) {
        Set<ConstraintViolation<DeleteOrderRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteOrderResponse(errors);
        }
        orderRepository.deleteById(request.getDeleteOrderId());
        return new DeleteOrderResponse(!orderRepository.existsById(request.getDeleteOrderId()));
    }
}
