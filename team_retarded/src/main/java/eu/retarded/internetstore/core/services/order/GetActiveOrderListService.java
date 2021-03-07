package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.GetActiveOrderListRequest;
import eu.retarded.internetstore.core.responses.order.GetActiveOrderListResponse;
import eu.retarded.internetstore.database.OrderRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;


@Service
public class GetActiveOrderListService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public GetActiveOrderListResponse execute(GetActiveOrderListRequest request) {
        Set<ConstraintViolation<GetActiveOrderListRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetActiveOrderListResponse(errors);
        }
        List<Order> orders;
        if (request.getPageable() == null) {
            orders = orderRepository.findOrderByUserIdAndStatusBetween
                    (request.getId(),1,3);
            return new GetActiveOrderListResponse(null, orders);
        }
        Page<Order> ordersPage = orderRepository.findOrderByUserIdAndStatusBetween
                (request.getId(),1,3, request.getPageable());
        return new GetActiveOrderListResponse(ordersPage, ordersPage.toList());
    }
}
