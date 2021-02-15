package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.AddOrderRequest;
import eu.retarded.internetstore.core.responses.order.AddOrderResponse;
import eu.retarded.internetstore.database.CartRepository;
import eu.retarded.internetstore.database.DeliveryRepository;
import eu.retarded.internetstore.database.OrderRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

@Component
public class AddOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    public AddOrderResponse execute(AddOrderRequest request) {
        Set<ConstraintViolation<AddOrderRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddOrderResponse(errors);
        }
        Order order = new Order(request.getName(),request.getSurname(),request.getAddress(),
                cartRepository.getOne(request.getCartId()),
                deliveryRepository.getOne(request.getDeliveryId()),
                userRepository.getOne(request.getUserId()),
                BigDecimal.valueOf(request.getTotalPrice()));
        return new AddOrderResponse(orderRepository.save(order));
    }
}
