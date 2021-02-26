package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.requests.order.GetByIdOrderRequest;
import eu.retarded.internetstore.core.responses.order.GetByIdOrderResponse;
import eu.retarded.internetstore.database.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class GetByIdOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public GetByIdOrderResponse execute(GetByIdOrderRequest request) {
        Set<ConstraintViolation<GetByIdOrderRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetByIdOrderResponse(errors);
        }
        return new GetByIdOrderResponse(orderRepository.findById(request.getId()).get());
    }
}
