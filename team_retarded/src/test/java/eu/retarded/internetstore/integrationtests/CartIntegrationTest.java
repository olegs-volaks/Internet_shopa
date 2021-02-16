package eu.retarded.internetstore.integrationtests;

import eu.retarded.internetstore.database.cart.CartDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class CartIntegrationTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    CartDatabase cartDatabase;

    @BeforeEach
    void setUp() {
        cartDatabase.clear();
    }

/*    @Test
    void add_cart_request() {
        AddCartService service = context.getBean(AddCartService.class);
        AddUserService addUserService = context.getBean(AddUserService.class);
        long id = addUserService.execute(new AddUserRequest("login", "password", 1, "Admin",
                "Admin2", "admin@inbox.lv")).getUserId();
        AddCartRequest request1 = new AddCartRequest(id);
        service.execute(request1);
        assertThat(cartDatabase.getList().size()).isEqualTo(1);

    }


    @Test
    void delete_cart_request() {
        AddCartService addCartService = context.getBean(AddCartService.class);
        AddUserService addUserService = context.getBean(AddUserService.class);
        DeleteCartService deleteCartService = context.getBean(DeleteCartService.class);
        long id = addUserService.execute(new AddUserRequest("login", "password", 1, "Admin",
                "surname", "admin@inbox.lv")).getUserId();
        long id1 = addUserService.execute(new AddUserRequest("login1", "password1", 2, "Admin1",
                "surname1", "admin1@inbox.lv")).getUserId();
        long id2 = addUserService.execute(new AddUserRequest("login2", "password2", 1, "Admin2",
                "surname2", "admin2@inbox.lv")).getUserId();
        AddCartRequest request = new AddCartRequest(id);
        AddCartRequest request1 = new AddCartRequest(id1);
        AddCartRequest request2 = new AddCartRequest(id2);
        long cartId = addCartService.execute(request).getId();
        long cartId1 = addCartService.execute(request1).getId();
        long cartId2 = addCartService.execute(request2).getId();
        deleteCartService.execute(new DeleteCartRequest(cartId));
        assertThat(cartDatabase.getList().size()).isEqualTo(2);
        assertThat(cartDatabase.getById(cartId).isEmpty()).isTrue();
        assertThat(cartDatabase.getById(cartId2).isEmpty()).isFalse();
    }

    @Test
    void get_cart_by_id_request() {
        AddCartService addCartService = context.getBean(AddCartService.class);
        AddUserService addUserService = context.getBean(AddUserService.class);
        GetByIdCartService getByIdCartService = context.getBean(GetByIdCartService.class);
        long id = addUserService.execute(new AddUserRequest("login", "password", 1, "Admin",
                "surname", "admin@inbox.lv")).getUserId();
        long id1 = addUserService.execute(new AddUserRequest("login1", "password1", 2, "Admin1",
                "surname1", "admin1@inbox.lv")).getUserId();
        long id2 = addUserService.execute(new AddUserRequest("login2", "password2", 1, "Admin2",
                "surname2", "admin2@inbox.lv")).getUserId();
        AddCartRequest request = new AddCartRequest(id);
        AddCartRequest request1 = new AddCartRequest(id1);
        AddCartRequest request2 = new AddCartRequest(id2);
        long cartId = addCartService.execute(request).getId();
        long cartId1 = addCartService.execute(request1).getId();
        long cartId2 = addCartService.execute(request2).getId();
        Cart cart = getByIdCartService.execute(new GetByIdCartRequest(cartId)).getCart();
        Cart cart1 = getByIdCartService.execute(new GetByIdCartRequest(cartId1)).getCart();
        assertThat(cart.getId()).isEqualTo(cartId);
        assertThat(cart1.getId()).isEqualTo(cartId1);

    }

    @Test
    void get_cart_list_request() {
        AddCartService addCartService = context.getBean(AddCartService.class);
        AddUserService addUserService = context.getBean(AddUserService.class);
        GetCartListService getCartListService = context.getBean(GetCartListService.class);
        long id = addUserService.execute(new AddUserRequest("login", "password", 1, "Admin",
                "surname", "admin@inbox.lv")).getUserId();
        long id1 = addUserService.execute(new AddUserRequest("login1", "password1", 2, "Admin1",
                "surname1", "admin1@inbox.lv")).getUserId();
        long id2 = addUserService.execute(new AddUserRequest("login2", "password2", 1, "Admin2",
                "surname2", "admin2@inbox.lv")).getUserId();
        AddCartRequest request = new AddCartRequest(id);
        AddCartRequest request1 = new AddCartRequest(id1);
        AddCartRequest request2 = new AddCartRequest(id2);
        addCartService.execute(request);
        addCartService.execute(request1);
        addCartService.execute(request2);
        assertThat(cartDatabase.getList().size()).isEqualTo(3);

    }*/
}
