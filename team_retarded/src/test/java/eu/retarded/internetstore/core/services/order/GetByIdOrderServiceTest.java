package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.order.GetByIdOrderRequest;
import eu.retarded.internetstore.core.responses.order.GetByIdOrderResponse;
import eu.retarded.internetstore.database.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class GetByIdOrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private GetByIdOrderService subject;

    @Test
    void get_order_success() {
        GetByIdOrderRequest request = new GetByIdOrderRequest(3L);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<GetByIdOrderRequest>>());
        Order order = new Order();
        User user= new User();
        Cart cart =new Cart();
        Delivery delivery= new Delivery();
        order.setId(3L);
        order.setClientName("Ivan");
        order.setClientSurname("Ivanov");
        order.setClientAddress("Novogrjazevo 1234567890");
        order.setUser(user);
        order.setDelivery(delivery);
        order.setCart(cart);
        order.setStatus(1);

        Order result = new Order();
        result.setClientName("Ivan");
        result.setClientSurname("Ivanov");
        result.setClientAddress("Novogrjazevo 1234567890");
        result.setDelivery(delivery);
        result.setUser(user);
        result.setCart(cart);
        result.setId(3L);
        result.setStatus(1);
        Mockito.when(orderRepository.findById(3L)).thenReturn(Optional.of(order));
        GetByIdOrderResponse getByIdOrderResponse = subject.execute(request);
        assertThat(getByIdOrderResponse.getOrder()).isEqualTo(result);
        Mockito.verify(orderRepository).findById(3L);
    }
}