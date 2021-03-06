package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.NewUserCartRequest;
import eu.retarded.internetstore.core.responses.cart.AddCartResponse;
import eu.retarded.internetstore.core.responses.user.NewUserCartResponse;
import eu.retarded.internetstore.core.services.cart.AddCartService;
import eu.retarded.internetstore.database.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class NewUserCartServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AddCartService addCartService;

    @InjectMocks
    private NewUserCartService subject;

    @Test
    void new_user_cart_success(){
        NewUserCartRequest request = new NewUserCartRequest(1L );
        Cart cart= new Cart();
        User user= new User();
        user.setId(1L);
        user.setCart(cart);

        Mockito.when(userRepository.getOne(request.getUserId())).thenReturn(user);
        Mockito.when(addCartService.execute(any())).thenReturn(new AddCartResponse(cart));
        NewUserCartResponse newUserCartResponse = subject.execute(request);
        assertThat(newUserCartResponse.getOldCart()).isEqualTo(cart);
        Mockito.verify(userRepository).save(user);
    }

}