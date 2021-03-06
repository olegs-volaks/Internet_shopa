package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.*;
import eu.retarded.internetstore.core.requests.order.AddOrderRequest;
import eu.retarded.internetstore.core.responses.order.AddOrderResponse;
import eu.retarded.internetstore.core.responses.user.NewUserCartResponse;
import eu.retarded.internetstore.core.services.user.NewUserCartService;
import eu.retarded.internetstore.database.DeliveryRepository;
import eu.retarded.internetstore.database.OrderRepository;
import eu.retarded.internetstore.database.ProductRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AddOrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private NewUserCartService newUserCartService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private AddOrderService subject;



    @Test
    void add_order_success() {
        AddOrderRequest request = new AddOrderRequest("Vasja", "Ivanov",
                "novogrjazevo12345678901234567890",1L,1L);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<AddOrderRequest>>());
        User user= new User();
        user.setId(1L);
        Product product= new Product();
        product.setId(1L);
        product.setCount(100);
        product.setPrice(BigDecimal.valueOf(10.25));

        Map<Product, Integer> products = new HashMap<>();
        products.put(product,5);
        Cart cart =new Cart();
        cart.setId(1L);
        cart.setProducts(products);
        Delivery delivery= new Delivery();
        delivery.setId(1L);
        delivery.setPrice(BigDecimal.valueOf(34.25));
        Mockito.when(userRepository.getOne(request.getUserId())).thenReturn(user);
        Mockito.when(deliveryRepository.getOne(request.getDeliveryId())).thenReturn(delivery);
        Mockito.when(productRepository.getOne(1L)).thenReturn(product);
        Mockito.when(newUserCartService.execute(any())).thenReturn(new NewUserCartResponse(cart));

        Order order = new Order();
        order.setClientName(request.getClientName());
        order.setClientSurname(request.getClientSurname());
        order.setClientAddress(request.getClientAddress());
        order.setDelivery(delivery);
        order.setUser(user);
        order.setCart(cart);
        order.setStatus(1);
        order.setTotalPrice(cart.getTotalPrice().add(delivery.getPrice()));

        Order result = new Order();
        result.setId(1L);
        result.setClientName("Vasja");
        result.setClientSurname("Ivanov");
        result.setClientAddress("novogrjazevo1234567890");
        result.setDelivery(delivery);
        result.setUser(user);
        result.setCart(cart);
        result.setStatus(1);
        result.setTotalPrice(cart.getTotalPrice().add(delivery.getPrice()));
        Mockito.when(orderRepository.save(order)).thenReturn(result);
        AddOrderResponse addOrderResponse = subject.execute(request);
        assertThat(addOrderResponse.getOrder()).isEqualTo(result);
        Mockito.verify(orderRepository).save(order);
    }


}