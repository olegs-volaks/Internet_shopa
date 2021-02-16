package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.requests.order.GetOrderListRequest;
import eu.retarded.internetstore.core.responses.order.GetOrderListResponse;
import eu.retarded.internetstore.database.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class GetOrderListService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public GetOrderListResponse execute(GetOrderListRequest request) {
        Set<ConstraintViolation<GetOrderListRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetOrderListResponse(errors, null);
        }

        return new GetOrderListResponse(null, orderRepository.findAll(request.getPageable()));
    }
}
