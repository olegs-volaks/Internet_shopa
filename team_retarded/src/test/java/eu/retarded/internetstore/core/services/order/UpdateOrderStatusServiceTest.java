package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.order.UpdateOrderStatusRequest;
import eu.retarded.internetstore.core.responses.order.UpdateOrderStatusResponse;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class UpdateOrderStatusServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private UpdateOrderStatusService subject;

    @Test
    void Show_all_order_success() {
        UpdateOrderStatusRequest request = new UpdateOrderStatusRequest(2L, 1);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<UpdateOrderStatusRequest>>());

        User user= new User();
        user.setId(3L);
        Cart cart =new Cart();
        Delivery delivery= new Delivery();
        Order result = new Order();
        result.setClientName("Ivan1");
        result.setClientSurname("Ivanov");
        result.setClientAddress("Novogrjazevo 1234567890");
        result.setDelivery(delivery);
        result.setUser(user);
        result.setCart(cart);
        result.setId(3L);
        result.setStatus(4);



        Mockito.when(orderRepository.getOne(2L)).thenReturn(result);
        UpdateOrderStatusResponse updateOrderStatusResponse = subject.execute(request);
        assertThat(updateOrderStatusResponse.getOrder()).isEqualTo( result);
        Mockito.verify(orderRepository).getOne(2L);
    }
}