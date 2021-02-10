package eu.retarded.internetstore.integrationtests;

import eu.retarded.internetstore.database.order.OrderDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class OrderIntegrationTest {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private OrderDatabase orderDatabase;

    @BeforeEach
    void setUp() {
        orderDatabase.clear();
    }

   /* @Test
    void add_category_test() {
        AddOrderService addOrderService = context.getBean(AddOrderService.class);
        long id1 = addOrderService.execute(new AddOrderRequest()).getOrderId();
        long id2 = addOrderService.execute(new AddOrderRequest()).getOrderId();
        long id3 = addOrderService.execute(new AddOrderRequest()).getOrderId();
        assertThat(id1).isLessThan(id2);
        assertThat(id2).isLessThan(id3);
        List<Order> result =orderDatabase.getList();
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void delete_category_by_id_test() {
        AddOrderService addOrderService = context.getBean(AddOrderService.class);
        DeleteOrderService deleteOrderService = context.getBean(DeleteOrderService.class);
        long id1 = addOrderService.execute(new AddOrderRequest()).getOrderId();
        long id2 = addOrderService.execute(new AddOrderRequest()).getOrderId();
        long id3 = addOrderService.execute(new AddOrderRequest()).getOrderId();
        //boolean firstResult = deleteOrderService.execute(new DeleteOrderRequest(id2)).isDeleted();
       // boolean secondResult = deleteOrderService.execute(new DeleteOrderRequest(id3 + 1)).isDeleted();
        //assertThat(firstResult).isTrue();
        //assertThat(secondResult).isFalse();
        List<Order> resultList = orderDatabase.getList();
        assertThat(resultList.size()).isEqualTo(2);
        assertThat(resultList).noneMatch(order -> order.getId() == id2);
    }*/
}

