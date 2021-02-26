package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.*;
import eu.retarded.internetstore.core.requests.order.AddOrderRequest;
import eu.retarded.internetstore.core.requests.user.NewUserCartRequest;
import eu.retarded.internetstore.core.responses.order.AddOrderResponse;
import eu.retarded.internetstore.core.services.user.NewUserCartService;
import eu.retarded.internetstore.database.DeliveryRepository;
import eu.retarded.internetstore.database.OrderRepository;
import eu.retarded.internetstore.database.ProductRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;

@Service
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
    private ProductRepository productRepository;

    @Autowired
    private Validator validator;

    @Transactional
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
        Delivery delivery = deliveryRepository.getOne(request.getDeliveryId());
        order.setDelivery(delivery);
        order.setTotalPrice(orderCart.getTotalPrice().add(delivery.getPrice()));
        order.setUser(activeUser);
        order.setStatus(1);
        Map<Product, Integer> products = orderCart.getProducts();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = productRepository.getOne(entry.getKey().getId());
            product.setCount(product.getCount() - entry.getValue());
        }

        return new AddOrderResponse(orderRepository.save(order));
    }
}
