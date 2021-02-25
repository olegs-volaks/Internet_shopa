package eu.retarded.internetstore.integrationtests;

import eu.retarded.internetstore.core.domain.Delivery;
import eu.retarded.internetstore.core.domain.Order;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.delivery.AddDeliveryRequest;
import eu.retarded.internetstore.core.requests.order.AddOrderRequest;
import eu.retarded.internetstore.core.requests.product.AddProductRequest;
import eu.retarded.internetstore.core.requests.user.AddProductToUserCartRequest;
import eu.retarded.internetstore.core.requests.user.RegisterUserRequest;
import eu.retarded.internetstore.core.services.delivery.AddDeliveryService;
import eu.retarded.internetstore.core.services.order.AddOrderService;
import eu.retarded.internetstore.core.services.product.AddProductService;
import eu.retarded.internetstore.core.services.user.AddProductToUserCartService;
import eu.retarded.internetstore.core.services.user.RegisterUserService;
import eu.retarded.internetstore.database.CartRepository;
import eu.retarded.internetstore.database.OrderRepository;
import eu.retarded.internetstore.database.ProductRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;


    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        userRepository.deleteAll();
        cartRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void test1() {
        RegisterUserService registerUserService = context.getBean(RegisterUserService.class);
        User user = registerUserService.execute(new RegisterUserRequest("Username", "1234567", "1234567", "Name",
                "Surname", "user@mail.com")).getUser();
        AddProductService addProductService = context.getBean(AddProductService.class);
        Product product1 = addProductService
                .execute(new AddProductRequest("Nameprod",
                        "asdsaaaaaaaaaaaaaaaaaaaaaaaaaaaaasssssssssssdddddddddasdasdasdasd", 100.25, 10))
                .getProduct();
        Product product2 = addProductService
                .execute(new AddProductRequest("Nameprod",
                        "asdsaaaaaaaaaaaaaaaaaaaaaaaaaaaaasssssssssssdddddddddasdasdasdasd", 100.50, 10))
                .getProduct();
        AddProductToUserCartService addProductToUserCartService = context.getBean(AddProductToUserCartService.class);
        addProductToUserCartService.execute(new AddProductToUserCartRequest(user.getId(), product1.getId(), 4));
        addProductToUserCartService.execute(new AddProductToUserCartRequest(user.getId(), product2.getId(), 2));
        AddDeliveryService addDeliveryService = context.getBean(AddDeliveryService.class);
        Delivery delivery = addDeliveryService
                .execute(new AddDeliveryRequest("Title", "Region", 1.50))
                .getDeliveryId();
        AddOrderService addOrderService = context.getBean(AddOrderService.class);
        AddOrderRequest addOrderRequest = new AddOrderRequest(
                "Ivan",
                "Ivanov",
                "Riga 325-5335",
                delivery.getId(),
                user.getId());
        Order order = addOrderService.execute(addOrderRequest).getOrder();
        assertThat(order.getTotalPrice().compareTo(BigDecimal.valueOf(603.50))).isEqualTo(0);
    }
}

