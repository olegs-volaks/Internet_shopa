package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.Role;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.cart.AddCartRequest;
import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.responses.user.AddUserResponse;
import eu.retarded.internetstore.core.services.cart.AddCartService;
import eu.retarded.internetstore.database.CartRepository;
import eu.retarded.internetstore.database.RoleRepository;
import eu.retarded.internetstore.database.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
class AddUserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private Validator validator;

    @Mock
    private AddCartService addCartService;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private AddUserService subject;

    @Test
    void add_user_success() {
        Set<Long> roles= new HashSet<>();
        roles.add(1L);
        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role());
        AddUserRequest request = new AddUserRequest("Igor12345", "1234567890",
                "1234567890","Ivan12345", "Ivanko123","jupiter@lmr.lv",roles);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<AddUserRequest>>());
        Cart cart = new Cart();
        cart.setId(1L);
        User user = new User();
        User result = new User();
        AddCartRequest addCartRequest=new AddCartRequest();
        Mockito.when(encoder.encode(request.getPassword())).thenReturn("1234567890");
        Mockito.when(cartRepository.getOne(1L)).thenReturn(cart);
        Mockito.when(addCartService.execute(addCartRequest).getCart()).thenReturn(cart);
        Mockito.when(userRepository.save(user)).thenReturn(result);
        Mockito.when(roleRepository.findAllById(request.getRolesId())).thenReturn(roleList);
        AddUserResponse addUserResponse = subject.execute(request);
        assertThat(addUserResponse.getUser()).isEqualTo(result);
        //Mockito.verify(productRepository).save(product);
    }
}