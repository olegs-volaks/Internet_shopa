package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.ChangeUserPasswordRequest;
import eu.retarded.internetstore.core.responses.user.ChangeUserPasswordResponse;
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
import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ChangeUserPasswordServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private Validator validator;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ChangeUserPasswordService subject;

    @Test
    void change_user_password_success() {
        ChangeUserPasswordRequest request = new ChangeUserPasswordRequest(1L, "123456789",
                "123456789");
        Mockito.when(validator.validate(request))
                .thenReturn(new HashSet<ConstraintViolation<ChangeUserPasswordRequest>>());

        User user = new User();
        User result =new User();
        result.setPassword("123456789");
        Mockito.when(userRepository.getOne(request.getUserId())).thenReturn(user);
        Mockito.when(passwordEncoder.encode(request.getNewPassword())).thenReturn("123456789");

        ChangeUserPasswordResponse changeUserPasswordResponse = subject.execute(request);
        assertThat(changeUserPasswordResponse.getUser()).isEqualTo(result);
    }
}