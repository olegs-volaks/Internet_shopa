package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Role;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.UpdateUserRequest;
import eu.retarded.internetstore.core.responses.user.UpdateUserResponse;
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
class UpdateUserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private UpdateUserService subject;

    @Test
    void Update_user_success(){
        UpdateUserRequest request = new UpdateUserRequest(10L, "Saturn888",
                "Ivanoff","jupiter@lmr.lv");
    Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<UpdateUserRequest>>());
    Set<Long> roles= new HashSet<>();
        roles.add(1L);
    Set<Role> roleList = new HashSet<>();
        roleList.add(new Role());
    User user1 = new User();
        user1.setStatus(1);
        user1.setName(request.getName());
        user1.setSurname(request.getSurname());
        user1.setEmail(request.getEmail());
        user1.setId(10L);
        user1.setRoles(roleList);

    User user2 = new User();
        user2.setId(10L);
        user2.setName("Saturn888");
        user2.setSurname("Ivanoff");
        user2.setEmail("jupiter@lmr.lv");
        user2.setRoles(roleList);
        user2.setStatus(1);


        Mockito.when(userRepository.getOne(request.getId())).thenReturn(user1);
        UpdateUserResponse updateUserResponse = subject.execute(request);
        assertThat(updateUserResponse.getUser()).isEqualTo(user2);

}
}