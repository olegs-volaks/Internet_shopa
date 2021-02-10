package eu.retarded.internetstore.integrationtests;

import eu.retarded.internetstore.core.requests.cart.AddCartRequest;
import eu.retarded.internetstore.core.requests.cart.DeleteCartRequest;
import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.services.cart.AddCartService;
import eu.retarded.internetstore.core.services.cart.DeleteCartService;
import eu.retarded.internetstore.core.services.user.AddUserService;
import eu.retarded.internetstore.database.cart.CartDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CartIntegrationTest {

    @Autowired ApplicationContext context;

    @Autowired CartDatabase cartDatabase;


    @BeforeEach
    void setUp() {
        cartDatabase.clear();
    }

    @Test
    void add_cart_request() {
        AddCartService service = context.getBean(AddCartService.class);
        AddUserService addUserService = context.getBean(AddUserService.class);
        long id = addUserService.execute(new AddUserRequest("login","pasword",1,"Admin",
                "Admin2","admin@inbox.lv")).getUserId();
        AddCartRequest request1 = new AddCartRequest(id);
        service.execute(request1);
        assertThat(cartDatabase.getList().size()).isEqualTo(1);

    }


    @Test
    void delete_cart_request() {
        AddCartService addCartService = context.getBean(AddCartService.class);
        DeleteCartService deleteCartService = context.getBean(DeleteCartService.class);
        AddCartRequest request = new AddCartRequest(1L);
        AddCartRequest request1 = new AddCartRequest(2L);
        AddCartRequest request2 = new AddCartRequest(3L);
        deleteCartService.execute(new DeleteCartRequest(2L));
        assertThat(cartDatabase.getList().size()).isEqualTo(2);
        assertThat(cartDatabase.getById(1L).isEmpty()).isFalse();
        assertThat(cartDatabase.getById(3L).isEmpty()).isFalse();
    }

    @Test
    void get_cart_by_id_request() {

    }

    @Test
    void search_cart_request() {

    }
}
