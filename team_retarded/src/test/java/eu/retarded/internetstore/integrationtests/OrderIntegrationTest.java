package eu.retarded.internetstore.integrationtests;

import eu.retarded.internetstore.core.requests.order.AddOrderRequest;
import eu.retarded.internetstore.core.requests.order.DeleteOrderRequest;
import eu.retarded.internetstore.core.services.order.AddOrderService;
import eu.retarded.internetstore.core.services.order.DeleteOrderService;
import eu.retarded.internetstore.database.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/*@SpringBootTest
public class OrderIntegrationTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    void show_list() {
        AddOrderService addOrderService = context.getBean(AddOrderService.class);
        AddOrderRequest request = new AddOrderRequest("admin", "admin12345", "riga", 1, 1,
                1, 10);
        addOrderService.execute(request);
        assertThat(orderRepository.findAll().size()).isEqualTo(1);
    }
    // long id1 = addOrderService.execute(new AddOrderRequest();
    // long id2 = addOrderService.execute(new AddOrderRequest()).getOrderId();
    // long id3 = addOrderService.execute(new AddOrderRequest()).getOrderId();
    //  List<Order> result = orderRepository.findAll();
    //  assertThat(result.size()).isEqualTo(3);

    /*
    @Test
    void delete_category_by_id_test() {
        AddOrderService addOrderService = context.getBean(AddOrderService.class);
        DeleteOrderService deleteOrderService = context.getBean(DeleteOrderService.class);
        AddOrderRequest request =  new AddOrderRequest("admin","admin12345","riga",1,1,
                1,10);
        addOrderService.execute(request);
        eu.retarded.internetstore.core.domain.Order id = addOrderService.execute(request).getOrder();
        deleteOrderService.execute(new DeleteOrderRequest(id.getId()));
        assertThat(orderRepository.findAll().size()).isEqualTo(1);
        assertThat(orderRepository.existsById(id.getId())).isTrue();

      //  long id1 = addOrderService.execute(new AddOrderRequest()).getOrderId();
      //  long id2 = addOrderService.execute(new AddOrderRequest()).getOrderId();
      //  long id3 = addOrderService.execute(new AddOrderRequest()).getOrderId();
        //boolean firstResult = deleteOrderService.execute(new DeleteOrderRequest(id2)).isDeleted();
       // boolean secondResult = deleteOrderService.execute(new DeleteOrderRequest(id3 + 1)).isDeleted();
        //assertThat(firstResult).isTrue();
        //assertThat(secondResult).isFalse();
       // List<Order> resultList = orderDatabase.getList();
       // assertThat(resultList.size()).isEqualTo(2);
       // assertThat(resultList).noneMatch(order -> order.getId() == id2);
    }

     */


