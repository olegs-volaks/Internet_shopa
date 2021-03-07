package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.domain.Role;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.GetUserListRequest;
import eu.retarded.internetstore.core.responses.user.GetUserListResponse;
import eu.retarded.internetstore.database.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class GetUserListServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private Validator validator;
    @InjectMocks
    private GetUserListService subject;

    @Test
    void show_user_list_success() {
        Pageable pageable= PageRequest.of(2, 1);
        GetUserListRequest request = new GetUserListRequest(pageable);
        Mockito.when(validator.validate(request)).thenReturn(new HashSet<ConstraintViolation<GetUserListRequest>>());
        Set<Long> roles= new HashSet<>();
        roles.add(1L);
        Set<Role> roleList = new HashSet<>();
        roleList.add(new Role());
        User user1 = new User();
        user1.setStatus(1);
        user1.setUsername("Saturn888");
        user1.setName("Igor12345");
        user1.setSurname("1234567890qwert");
        user1.setEmail("delfi@lmt.lv");
        user1.setPassword("1234567890");
        user1.setId(1L);
        user1.setRoles(roleList);

        User user2 = new User();
        user2.setId(1L);
        user2.setUsername("Saturn888");
        user2.setName("Igor12345");
        user2.setSurname("1234567890qwert");
        user2.setEmail("delfi@lmt.lv");
        user2.setPassword("1234567890");
        user2.setRoles(roleList);
        user2.setStatus(1);
        List<User> resultList=new ArrayList<>();
        resultList.add(user1);
        resultList.add(user2);
        Page<User> resultPage =new PageImpl<>(resultList);

        Mockito.when(userRepository.findAll(pageable)).thenReturn(resultPage);
        GetUserListResponse getUserListResponse = subject.execute(request);
        assertThat(getUserListResponse.getList()).isEqualTo( resultList);
        assertThat(getUserListResponse.getPage()).isEqualTo( resultPage);
        Mockito.verify(userRepository).findAll(pageable);
    }
}