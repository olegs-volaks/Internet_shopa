package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.requests.order.DeleteOrderRequest;
import eu.retarded.internetstore.core.responses.order.DeleteOrderResponse;
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
class DeleteOrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private DeleteOrderService subject;

    @Test
    void delete_order_success() {
        DeleteOrderRequest request = new DeleteOrderRequest(2l);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<DeleteOrderRequest>>());

        Mockito.when(orderRepository.existsById(2L)).thenReturn(false);
        DeleteOrderResponse deleteOrderResponse = subject.execute(request);
        assertThat(deleteOrderResponse.isOrderDeleted()).isEqualTo(true);
        Mockito.verify(orderRepository).deleteById(2L);
    }

}