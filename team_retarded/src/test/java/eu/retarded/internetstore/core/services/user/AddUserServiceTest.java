package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.Role;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.responses.cart.AddCartResponse;
import eu.retarded.internetstore.core.responses.user.AddUserResponse;
import eu.retarded.internetstore.core.services.cart.AddCartService;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;


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
        user.setUsername(request.getUserName());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRoles(new HashSet<>(roleList));
        user.setCart(cart);
        user.setStatus(1);

        User result = new User();
        result.setId(1L);
        result.setUsername("Igor12345");
        result.setPassword("1234567890");
        result.setCart(cart);
        result.setName("Ivan12345");
        result.setSurname("Ivanko123");
        result.setEmail("jupiter@lmr.lv");
        result.setCart(cart);
        result.setRoles(new HashSet<>(roleList));
        result.setStatus(1);

        Mockito.when(encoder.encode(request.getPassword())).thenReturn("1234567890");
        Mockito.when(addCartService.execute(any())).thenReturn(new AddCartResponse(cart));
        Mockito.when(roleRepository.findAllById(request.getRolesId())).thenReturn(roleList);
        Mockito.when(userRepository.save(user)).thenReturn(result);

        AddUserResponse addUserResponse = subject.execute(request);
        assertThat(addUserResponse.getUser()).isEqualTo(result);
        Mockito.verify(userRepository).save(user);
    }
}