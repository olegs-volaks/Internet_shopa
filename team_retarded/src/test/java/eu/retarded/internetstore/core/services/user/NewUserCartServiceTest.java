package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.AddCartRequest;
import eu.retarded.internetstore.core.requests.user.NewUserCartRequest;
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

@ExtendWith(MockitoExtension.class)
class NewUserCartServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private AddCartService addCartService;

    @InjectMocks
    private NewUserCartService subject;

    @Test
    void show_user_list_success(){
        NewUserCartRequest request = new NewUserCartRequest(1L );
        User user= new User();
        Cart cart= new Cart();
        AddCartRequest request1=new AddCartRequest();
        Mockito.when(userRepository.getOne(request.getUserId())).thenReturn(user);
        Mockito.when(addCartService.execute(request1).getCart()).thenReturn(cart);
        NewUserCartResponse newUserCartResponse = subject.execute(request);
        assertThat(newUserCartResponse.getOldCart()).isEqualTo(user);
        Mockito.verify(userRepository).save(user);
    }

}