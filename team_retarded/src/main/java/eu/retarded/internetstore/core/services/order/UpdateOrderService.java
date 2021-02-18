package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.requests.order.UpdateOrderRequest;
import eu.retarded.internetstore.core.responses.order.UpdateOrderResponse;
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
public class UpdateOrderService {

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


    public UpdateOrderResponse execute(UpdateOrderRequest request) {
        Set<ConstraintViolation<UpdateOrderRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateOrderResponse(errors);
        }

        Order resultOrder = new Order();
        resultOrder.setId(request.getId());
        resultOrder.setName(request.getName());
        resultOrder.setSurname(request.getSurname());
        resultOrder.setTotalPrice(BigDecimal.valueOf(request.getTotalPrice()));
        resultOrder.setCart(cartRepository.getOne(request.getCartId()));
        resultOrder.setDelivery(deliveryRepository.getOne(request.getDeliveryId()));
        resultOrder.setUser(userRepository.getOne(request.getUserId()));
        orderRepository.save(resultOrder);
        return new UpdateOrderResponse(resultOrder);
    }
}
