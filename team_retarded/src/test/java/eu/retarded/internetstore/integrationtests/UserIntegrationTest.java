package eu.retarded.internetstore.integrationtests;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
public class UserIntegrationTest {

    @Autowired
    private ApplicationContext context;

//    @Autowired
//    private UsersDatabase usersDatabase;
//
//    @Autowired
//    private CartDatabase cartDatabase;
//
//    @BeforeEach
//    void setUp() {
//        cartDatabase.clear();
//        usersDatabase.clear();
//    }

/*    @Test
    void add_user_test() {
        AddUserService addUserService = context.getBean(AddUserService.class);
        GetUsersListService getUsersListService = context.getBean(GetUsersListService.class);
        long id1 = addUserService.execute(new RegisterUserRequest("First", "Password 1", 1, "Jon",
                "Dou", "jond@mail.com")).getUserId();
        long id2 = addUserService.execute(new RegisterUserRequest("Second", "Password 2", 1, "Jon",
                "Dou", "jond@mail.com")).getUserId();
        long id3 = addUserService.execute(new RegisterUserRequest("Third", "Password 3", 1, "Jon",
                "Dou", "jond@mail.com")).getUserId();
        assertThat(id1).isLessThan(id2);
        assertThat(id2).isLessThan(id3);
        List<User> result = getUsersListService.execute(new GetUserListRequest()).getUsers();
        assertThat(result.size()).isEqualTo(3);
    }

    @Test
    void delete_user_test() {
        AddUserService addUserService = context.getBean(AddUserService.class);
        DeleteUserService deleteUserService = context.getBean(DeleteUserService.class);
        GetUsersListService getUsersListService = context.getBean(GetUsersListService.class);
        long id1 = addUserService.execute(new RegisterUserRequest("First", "Password 1", 1, "Jon",
                "Dou", "jond@mail.com")).getUserId();
        long id2 = addUserService.execute(new RegisterUserRequest("Second", "Password 2", 1, "Jon",
                "Dou", "jond@mail.com")).getUserId();
        long id3 = addUserService.execute(new RegisterUserRequest("Third", "Password 3", 1, "Jon",
                "Dou", "jond@mail.com")).getUserId();
        boolean firstResult = deleteUserService.execute(new DeleteUserRequest(id2)).isDeleted();
        boolean secondResult = deleteUserService.execute(new DeleteUserRequest(id3 + 1)).isDeleted();
        assertThat(firstResult).isTrue();
        assertThat(secondResult).isFalse();
        List<User> resultList = getUsersListService.execute(new GetUserListRequest()).getUsers();
        assertThat(resultList.size()).isEqualTo(2);
        assertThat(resultList).noneMatch(user -> user.getId() == id2);
    }

    @Test
    void update_user_test() {
        AddUserService addUserService = context.getBean(AddUserService.class);
        UpdateUserService updateUserService = context.getBean(UpdateUserService.class);
        GetUserByIdService getUserByIdService = context.getBean(GetUserByIdService.class);
        long id1 = addUserService.execute(new RegisterUserRequest("First", "Password 1", 1, "Name 1",
                "Surname 1", "mail1@mail.com")).getUserId();
        long id2 = addUserService.execute(new RegisterUserRequest("Second", "Password 2", 1, "Name 2",
                "Surname 2", "mail1@mail.com")).getUserId();
        String password = usersDatabase.getUserById(id2).get().getPassword();
        updateUserService.execute(new UpdateUserRequest(id2, 2, "Name3", "Surname3", "mail3@mail.com"));
        User result = getUserByIdService.execute(new GetUserByIdRequest(id2)).getUser();
        User expecting = new User();
        expecting.setId(id2);
        expecting.setLogin("Second");
        expecting.setPassword(password);
        expecting.setRole(2);
        expecting.setName("Name3");
        expecting.setSurname("Surname3");
        expecting.setEmail("mail3@mail.com");
        assertThat(result).isEqualTo(expecting);
    }

    @Test
    void change_user_password_test() {
        AddUserService addUserService = context.getBean(AddUserService.class);
        ChangeUserPasswordService changeUserPasswordService = context.getBean(ChangeUserPasswordService.class);
        GetUserByIdService getUserByIdService = context.getBean(GetUserByIdService.class);
        long id1 = addUserService.execute(new RegisterUserRequest("First", "Password 1", 1, "Name 1",
                "Surname 1", "mail1@mail.com")).getUserId();
        String firstPassword = usersDatabase.getUserById(id1).get().getPassword();
        ChangeUserPasswordResponse result = changeUserPasswordService.execute(new ChangeUserPasswordRequest(id1,
                "Password 1", "newPassword", "newPassword"));
        String secondPassword = usersDatabase.getUserById(id1).get().getPassword();
        assertThat(firstPassword.equals(secondPassword)).isFalse();
        assertThat(firstPassword.length()).isEqualTo(60);
        assertThat(secondPassword.length()).isEqualTo(60);
        assertThat(result.hasErrors()).isFalse();
        assertThat(result.getUserId()).isEqualTo(id1);
    }*/
}
