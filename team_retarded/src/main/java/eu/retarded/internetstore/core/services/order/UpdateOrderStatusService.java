package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.UpdateOrderStatusRequest;
import eu.retarded.internetstore.core.responses.order.UpdateOrderStatusResponse;
import eu.retarded.internetstore.database.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class UpdateOrderStatusService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public UpdateOrderStatusResponse execute(UpdateOrderStatusRequest request) {
        Set<ConstraintViolation<UpdateOrderStatusRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateOrderStatusResponse(errors);
        }

        Order resultOrder = orderRepository.getOne(request.getId());
        resultOrder.setStatus(request.getStatus());
        return new UpdateOrderStatusResponse(resultOrder);
    }
}
