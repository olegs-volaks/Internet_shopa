package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.Product;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.UpdateCountInUsersCartRequest;
import eu.retarded.internetstore.core.responses.user.UpdateCountInUsersCartResponse;
import eu.retarded.internetstore.database.CartRepository;
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
class UpdateCountInUsersCartServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private UpdateCountInUsersCartService subject;

    @Test
    void Update_count_in_users_cart_success() {
        UpdateCountInUsersCartRequest request = new UpdateCountInUsersCartRequest(10L, 10L,
                5);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<UpdateCountInUsersCartRequest>>());
        Product product = new Product("ford", "1234567890qwertyuiopasdfghjklzxcvbnm1234567890",
                345,5);


        Cart cart = new Cart();
        cart.setId(1L);
        User user = new User();
        user.setCart(cart);
        Map<Product, Integer> productsOld= new HashMap<>();
        productsOld.put(product, 2);
        cart.setProducts(productsOld);
        Cart result = new Cart();
        result.setId(1L);
        Map<Product, Integer> productsNew= new HashMap<>();
        productsNew.put(product, request.getCount());
        result.setProducts(productsNew);
        Mockito.when(productRepository.getOne(10L)).thenReturn(product);
        Mockito.when(userRepository.getOne(10L)).thenReturn(user);
        Mockito.when(cartRepository.getOne(1L)).thenReturn(cart);
        UpdateCountInUsersCartResponse updateCountInUsersCartResponse = subject.execute(request);
        assertThat(updateCountInUsersCartResponse.getCart()).isEqualTo(result);
    }
}