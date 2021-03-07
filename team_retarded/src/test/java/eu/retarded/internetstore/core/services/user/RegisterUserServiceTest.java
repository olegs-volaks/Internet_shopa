package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Cart;
import eu.retarded.internetstore.core.domain.Role;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.RegisterUserRequest;
import eu.retarded.internetstore.core.responses.user.AddUserResponse;
import eu.retarded.internetstore.core.responses.user.RegisterUserResponse;
import eu.retarded.internetstore.database.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RegisterUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Validator validator;

    @Mock
    private AddUserService addUserService;

    @InjectMocks
    private RegisterUserService subject;

    @Test
    void new_user_register_success(){
        RegisterUserRequest request = new RegisterUserRequest("Igor12345", "1234567890",
                "1234567890","Ivan12345", "Ivanko123","jupiter@lmr.lv");
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<RegisterUserRequest>>());
        Cart cart= new Cart();
        User user = new User();
        user.setUsername(request.getUserName());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
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

        List<Role> roleList = new ArrayList<>();
        roleList.add(new Role());
        result.setRoles(new HashSet<>(roleList));
        result.setStatus(1);
        Mockito.when(addUserService.execute(any())).thenReturn(new AddUserResponse(user));
        Mockito.when(userRepository.save(user)).thenReturn(result);

        RegisterUserResponse registerUserResponse = subject.execute(request);
        assertThat(registerUserResponse.getUser()).isEqualTo(result);
        Mockito.verify(userRepository).save(user);
    }
}