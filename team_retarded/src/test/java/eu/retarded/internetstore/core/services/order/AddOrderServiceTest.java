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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
                "novogrjazevo1234567890",1L,1L);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<AddOrderRequest>>());
        Order order = new Order();
        User user= new User();
        user.setId(1L);
        Product product= new Product();
        Cart cart =new Cart();
        Cart cart0 =new Cart();
        cart.setId(1L);
        Map<Product, Integer> products= new HashMap<>();
        products.put(product,5);
        cart.setProducts(products);
        Delivery delivery= new Delivery();
        Mockito.when(userRepository.getOne(request.getUserId())).thenReturn(user);
        Mockito.when(deliveryRepository.getOne(request.getDeliveryId())).thenReturn(delivery);
        Mockito.when(productRepository.getOne(1L)).thenReturn(product);
        NewUserCartRequest newUserCartRequest=new NewUserCartRequest(1L);
        //Mockito.when(newUserCartService.execute()).thenReturn(cart);

        order.setClientName(request.getClientName());
        order.setClientSurname(request.getClientSurname());
        order.setClientAddress(request.getClientAddress());
        order.setUser(user);
        order.setDelivery(delivery);
        order.setCart(cart0);

        Order result = new Order();
        result.setClientName("Vasja");
        result.setClientSurname("Ivanov");
        result.setClientAddress("novogrjazevo1234567890");
        result.setDelivery(delivery);
        result.setUser(user);
        result.setCart(cart);
        result.setId(1L);
        result.setStatus(1);
        Mockito.when(orderRepository.save(order)).thenReturn(order);
        AddOrderResponse addOrderResponse = subject.execute(request);
        assertThat(addOrderResponse.getOrder()).isEqualTo(result);
        Mockito.verify(orderRepository).save(order);
    }


}