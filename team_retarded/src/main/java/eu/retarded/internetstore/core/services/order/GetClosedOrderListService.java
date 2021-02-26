package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.GetClosedOrderListRequest;
import eu.retarded.internetstore.core.responses.order.GetClosedOrderListResponse;
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
public class GetClosedOrderListService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public GetClosedOrderListResponse execute(GetClosedOrderListRequest request) {
        Set<ConstraintViolation<GetClosedOrderListRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetClosedOrderListResponse(errors);
        }
        List<Order> orders;
        if (request.getPageable() == null) {
            orders = orderRepository.findOrderByUserIdAndStatusEqualsOrStatusEquals
                    (request.getId(),0,4);
            return new GetClosedOrderListResponse(null, orders);
        }
        Page<Order> ordersPage = orderRepository.findOrderByUserIdAndStatusEqualsOrStatusEquals
                (request.getId(),0,4,request.getPageable());
        return new GetClosedOrderListResponse(ordersPage, ordersPage.toList());
    }
}
