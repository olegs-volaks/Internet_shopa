package eu.retarded.internetstore.core.services.validators.user;

import eu.retarded.internetstore.core.requests.user.DeleteUserRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class DeleteUserValidatorTest {

    @Mock
    private UsersDatabase usersDatabase;

    @InjectMocks
    private DeleteUserValidator subject;

    @Test
    void delete_user_by_id() {
        long id = 3;
        Mockito.when(usersDatabase.isExist(id)).thenReturn(true);
        List<CoreError> actual = subject.validate(new DeleteUserRequest(id));
        assertThat(actual).isEmpty();
    }

    @Test
    void delete_user_failed_or_negative() {
        long id = 0;
        List<CoreError> actual = subject.validate(new DeleteUserRequest(id));
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(coreError -> coreError.getField().equals("ID") &&
                coreError.getMessage().equals("Must not be empty, negative or fractional"));
    }

    @Test
    void if_user_is_not_exist_in_database() {
        long id = 3;
        Mockito.when(usersDatabase.isExist(id)).thenReturn(false);
        List<CoreError> actual = subject.validate(new DeleteUserRequest(id));
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(coreError -> coreError.getField().equals("ID") &&
                coreError.getMessage().equals("The user with the given id does not exist"));
    }
}