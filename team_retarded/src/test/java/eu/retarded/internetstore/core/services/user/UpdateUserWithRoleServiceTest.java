package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Role;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.UpdateUserWithRoleRequest;
import eu.retarded.internetstore.core.responses.user.UpdateUserWithRoleResponse;
import eu.retarded.internetstore.database.RoleRepository;
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
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class UpdateUserWithRoleServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private Validator validator;

    @InjectMocks
    private UpdateUserWithRoleService subject;

    @Test
    void Update_user_success(){

        Role role1 =new Role();
        role1.setId(1L);
        role1.setName("guest");
        Role role2 =new Role();
        role1.setId(2L);
        role1.setName("admin");
        Set<Long> roles= new HashSet<>();
        roles.add(1L);
        List<Role> roleList1 = new ArrayList<>();
        roleList1.add(role1);
        roleList1.add(role2);
        List<Role> roleList2 = new ArrayList<>();
        roleList2.add(role1);

        UpdateUserWithRoleRequest request = new UpdateUserWithRoleRequest(10L, "Saturn888",
                "Ivanoff","jupiter@lmr.lv",roles);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<UpdateUserWithRoleRequest>>());

        User user1 = new User();
        user1.setStatus(1);
        user1.setName(request.getName());
        user1.setSurname(request.getSurname());
        user1.setEmail(request.getEmail());
        user1.setId(10L);
        user1.setRoles(new HashSet<>(roleList1));

        User user2 = new User();
        user2.setId(10L);
        user2.setName("Saturn888");
        user2.setSurname("Ivanoff");
        user2.setEmail("jupiter@lmr.lv");
        user2.setRoles(new HashSet<>(roleList2));
        user2.setStatus(1);


       Mockito.when(userRepository.getOne(request.getId())).thenReturn(user1);
       Mockito.when(roleRepository.findAllById(request.getRolesId())).thenReturn(roleList2);
       UpdateUserWithRoleResponse updateUserWithRoleResponse = subject.execute(request);
       assertThat(updateUserWithRoleResponse.getUserId()).isEqualTo(user2);

    }
}
