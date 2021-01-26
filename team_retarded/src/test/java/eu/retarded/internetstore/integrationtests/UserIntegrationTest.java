package eu.retarded.internetstore.integrationtests;


import eu.retarded.internetstore.config.ApplicationConfiguration;
import eu.retarded.internetstore.core.domain.User;
import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.requests.user.DeleteUserRequest;
import eu.retarded.internetstore.core.services.user.AddUserService;
import eu.retarded.internetstore.core.services.user.DeleteUserService;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class UserIntegrationTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UsersDatabase usersDatabase;

    @BeforeEach
    void setUp() {
        usersDatabase.clear();
    }

    @Test
    void add_user_test() {
        AddUserService addUserService = context.getBean(AddUserService.class);
        long id1 = addUserService.execute(new AddUserRequest("First", "Password 1")).getUserId();
        long id2 = addUserService.execute(new AddUserRequest("Second", "Password 2")).getUserId();
        long id3 = addUserService.execute(new AddUserRequest("Third", "Password 3")).getUserId();
        assertThat(id1).isLessThan(id2);
        assertThat(id2).isLessThan(id3);
        List<User> result = usersDatabase.getList();
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void delete_user_test() {
        AddUserService addUserService = context.getBean(AddUserService.class);
        DeleteUserService deleteUserService = context.getBean(DeleteUserService.class);
        long id1 = addUserService.execute(new AddUserRequest("First", "Password 1")).getUserId();
        long id2 = addUserService.execute(new AddUserRequest("Second", "Password 2")).getUserId();
        long id3 = addUserService.execute(new AddUserRequest("Third", "Password 3")).getUserId();
        boolean firstResult = deleteUserService.execute(new DeleteUserRequest(id2)).isDeleted();
        boolean secondResult = deleteUserService.execute(new DeleteUserRequest(id3 + 1)).isDeleted();
        assertThat(firstResult).isTrue();
        assertThat(secondResult).isFalse();
        List<User> resultList = usersDatabase.getList();
        assertThat(resultList.size()).isEqualTo(2);
        assertThat(resultList).noneMatch(user -> user.getId() == id2);
    }
}
