package eu.retarded.internetstore.core.services.user;

import eu.retarded.internetstore.core.requests.user.DeleteUserRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import eu.retarded.internetstore.core.responses.user.DeleteUserResponse;
import eu.retarded.internetstore.core.services.validators.user.DeleteUserValidator;
import eu.retarded.internetstore.database.user.UsersDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class DeleteUserServiceTest {

    @Mock
    private DeleteUserValidator validator;
    @Mock
    private UsersDatabase userDatabase;
    @InjectMocks
    private DeleteUserService subject;

    @Test
    public  void should_return_response_with_errors_when_validator_fails() {
        DeleteUserRequest request = new DeleteUserRequest(0);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("ID","Must not be empty,negative or fractional"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        DeleteUserResponse response = subject.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(),1);
        assertEquals(response.getErrors().get(0).getField(),"ID");
        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(userDatabase);
    }

    @Test
    public void should_delete_user_with_id_from_database () {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(userDatabase.delete(1L)).thenReturn(true);
        DeleteUserRequest request = new DeleteUserRequest(1L);
        DeleteUserResponse response = subject.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isDeleted());

    }
}