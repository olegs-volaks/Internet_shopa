package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.user.AddUserResponse;
import eu.retarded.internetstore.core.services.validators.user.AddUserValidator;
import eu.retarded.internetstore.database.user.UsersDatabase;
import eu.retarded.internetstore.matchers.UserMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@ExtendWith(MockitoExtension.class)
public class AddUserServiceTest {

    @Mock
    private UsersDatabase userDatabase;
    @Mock
    private AddUserValidator validator;
    @InjectMocks
    private AddUserService subject;

    @Test
    public void should_return_response_with_errors_when_validation_fails() {
        AddUserRequest request = new AddUserRequest("123", "region123456");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Login", "Must be between 3 and 32 characters"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddUserResponse response = subject.execute(request);
        Assertions.assertTrue(response.hasErrors());
        Assertions.assertEquals(response.getErrors().size(), 1);
        Assertions.assertEquals(response.getErrors().get(0).getField(), "Login");
        Assertions.assertEquals(response.getErrors().get(0).getMessage(), "Must be between 3 and 32 characters");
        Mockito.verifyNoInteractions(userDatabase);
        Mockito.verify(validator).validate(request);
    }

    @Test
    public void add_user_to_database() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddUserRequest request = new AddUserRequest("Title", "Region1234567");
        AddUserResponse response = subject.execute(request);
        Assertions.assertFalse(response.hasErrors());
        Mockito.verify(userDatabase).add(argThat(new UserMatcher("Title", "Region1234567")));
    }
}