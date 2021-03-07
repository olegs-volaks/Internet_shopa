package eu.retarded.internetstore.core.services.order;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.order.GetOrderListRequest;
import eu.retarded.internetstore.core.responses.order.GetOrderListResponse;
import eu.retarded.internetstore.database.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class GetOrderListServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private GetOrderListService subject;

    @Test
    void Show_all_order_success() {
        Pageable pageable= PageRequest.of(1, 2);
        GetOrderListRequest request = new GetOrderListRequest(pageable);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<GetOrderListRequest>>());
        Order order1 = new Order();
        User user= new User();
        user.setId(3L);
        Cart cart =new Cart();
        Delivery delivery= new Delivery();
        order1.setId(1L);
        order1.setClientName("Ivan2");
        order1.setClientSurname("Ivanov");
        order1.setClientAddress("Novogrjazevo 1234567890");
        order1.setUser(user);
        order1.setDelivery(delivery);
        order1.setCart(cart);
        order1.setStatus(0);

        Order result = new Order();
        result.setClientName("Ivan1");
        result.setClientSurname("Ivanov");
        result.setClientAddress("Novogrjazevo 1234567890");
        result.setDelivery(delivery);
        result.setUser(user);
        result.setCart(cart);
        result.setId(3L);
        result.setStatus(4);

        List<Order> resultList=new ArrayList<>();
        resultList.add(order1);
        resultList.add(result);
        Page<Order> resultPage =new PageImpl<>(resultList);


        Mockito.when(orderRepository.findAll(pageable)).thenReturn(resultPage);
        GetOrderListResponse getOrderListResponse = subject.execute(request);
        assertThat(getOrderListResponse.getOrdersList()).isEqualTo( resultList);
        assertThat(getOrderListResponse.getOrdersList().size()).isEqualTo(2);
        assertThat(getOrderListResponse.getOrdersPage()).isEqualTo( resultPage);
        Mockito.verify(orderRepository).findAll(pageable);
    }

}