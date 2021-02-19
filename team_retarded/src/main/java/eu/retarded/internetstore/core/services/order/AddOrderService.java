package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.order.AddOrderRequest;
import eu.retarded.internetstore.core.requests.user.NewUserCartRequest;
import eu.retarded.internetstore.core.responses.order.AddOrderResponse;
import eu.retarded.internetstore.core.services.user.NewUserCartService;
import eu.retarded.internetstore.database.DeliveryRepository;
import eu.retarded.internetstore.database.OrderRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class AddOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NewUserCartService newUserCartService;

    @Autowired
    private Validator validator;

    public AddOrderResponse execute(AddOrderRequest request) {
        Set<ConstraintViolation<AddOrderRequest>> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddOrderResponse(errors);
        }
        User activeUser = userRepository.getOne(request.getUserId());
        Order order = new Order();
        order.setClientName(request.getClientName());
        order.setClientSurname(request.getClientSurname());
        order.setClientAddress(request.getClientAddress());
        Cart orderCart = newUserCartService.execute(new NewUserCartRequest(activeUser.getId())).getOldCart();
        order.setCart(orderCart);
        order.setDelivery(deliveryRepository.getOne(request.getUserId()));
        order.setTotalPrice(orderCart.getTotalPrice());
        order.setStatus(1);
        return new AddOrderResponse(orderRepository.save(order));
    }
}
