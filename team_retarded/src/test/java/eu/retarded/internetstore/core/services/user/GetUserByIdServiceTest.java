package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Role;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.GetUserByIdRequest;
import eu.retarded.internetstore.core.responses.user.GetUserByIdResponse;
import eu.retarded.internetstore.database.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class GetUserByIdServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private GetUserByIdService subject;

    @Test
    void get_user_success() {
        GetUserByIdRequest request = new GetUserByIdRequest(1L);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<GetUserByIdRequest>>());
        Set<Long> roles= new HashSet<>();
        roles.add(1L);
        Set<Role> roleList = new HashSet<>();
        roleList.add(new Role());
        User user = new User();
        user.setStatus(1);
        user.setUsername("Saturn888");
        user.setName("Igor12345");
        user.setSurname("1234567890qwert");
        user.setEmail("delfi@lmt.lv");
        user.setPassword("1234567890");
        user.setId(1L);
        user.setRoles(roleList);

        User result = new User();
        result.setId(1L);
        result.setUsername("Saturn888");
        result.setName("Igor12345");
        result.setSurname("1234567890qwert");
        result.setEmail("delfi@lmt.lv");
        result.setPassword("1234567890");
        result.setRoles(roleList);
        result.setStatus(1);
        Mockito.when(userRepository.getOne(request.getUserId())).thenReturn(user);
        GetUserByIdResponse getUserByIdResponse = subject.execute(request);
        assertThat(getUserByIdResponse.getUser()).isEqualTo(result);
        //Mockito.verify(userRepository).findById(3L);
    }
}