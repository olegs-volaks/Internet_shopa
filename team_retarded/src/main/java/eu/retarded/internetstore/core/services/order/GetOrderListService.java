package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.GetOrderListRequest;
import eu.retarded.internetstore.core.responses.order.GetOrderListResponse;
import eu.retarded.internetstore.database.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class GetOrderListService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Validator validator;

    @Transactional
    public GetOrderListResponse execute(GetOrderListRequest request) {
        Set<ConstraintViolation<GetOrderListRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetOrderListResponse(errors);
        }
        List<Order> orders;
        if (request.getPageable() == null) {
            orders = orderRepository.findAll();
            return new GetOrderListResponse(null, orders);
        }
        Page<Order> ordersPage = orderRepository.findAll(request.getPageable());
        return new GetOrderListResponse(ordersPage, ordersPage.toList());
    }
}
