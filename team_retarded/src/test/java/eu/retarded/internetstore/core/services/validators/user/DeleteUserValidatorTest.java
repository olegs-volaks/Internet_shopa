package eu.retarded.internetstore.core.services.validators.user;

import eu.retarded.internetstore.core.requests.user.DeleteUserRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteUserValidatorTest {

    private final DeleteUserValidator subject = new DeleteUserValidator();

    @Test
    void delete_user_by_id() {
        List<CoreError> actual = subject.validate(new DeleteUserRequest(3));
        assertThat(actual).isEmpty();
    }

    @Test
    void delete_user_failed_or_negative() {
        List<CoreError> actual = subject.validate(new DeleteUserRequest(0));
        assertThat(actual).isNotEmpty();
        assertThat(actual).allMatch(coreError -> coreError.getField().equals("ID") &&
                coreError.getMessage().equals("Must not be empty, negative or fractional"));
    }
}