package eu.retarded.internetstore.core.services.validators.user;


import eu.retarded.internetstore.core.requests.user.AddUserRequest;
import eu.retarded.internetstore.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AddUserValidatorTest {

    private AddUserValidator subject;

    @BeforeEach
    void setSubject() {
        subject = new AddUserValidator();
    }

    @Test
    void validate_Login_is_less_than_3_characters() {
        AddUserRequest request = new AddUserRequest("Ma", "description11");
        List<CoreError> result = subject.validate(request);
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Login") &&
                coreError.getMessage().equals("Must be between 3 and 32 characters"));
    }

    @Test
    void validate_Login_is_more_than_3_characters() {
        AddUserRequest request = new AddUserRequest("Snickers", "description");
        List<CoreError> result = subject.validate(request);
        assertThat(result).isEmpty();
    }

    @Test
    void validate_Login_is_more_than_32_characters() {
        AddUserRequest request = new AddUserRequest("title111111111111111111111111111111111111111111111111111111" +
                "1111111111111111111111111111111111111111111111", "International");
        List<CoreError> result = subject.validate(request);
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Login") &&
                coreError.getMessage().equals("Must be between 3 and 32 characters"));
    }

    @Test
    void validate_region_is_less_than_6_characters() {
        AddUserRequest request = new AddUserRequest("Roma", "Inter");
        List<CoreError> result = subject.validate(request);
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Password") &&
                coreError.getMessage().equals("Must be between 6 and 32 characters"));
    }

    @Test
    void validate_password_is_more_than_6_characters() {
        AddUserRequest request = new AddUserRequest("Roma","Internacional");
        List<CoreError> result = subject.validate(request);
        assertThat(result).isEmpty();
    }

    @Test
    void validate_password_is_empty() {
        AddUserRequest request = new AddUserRequest("Mars", "");
        List<CoreError> result = subject.validate(request);
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Password") &&
                coreError.getMessage().equals("Must not be empty!"));
    }

    @Test
    void validate_login_is_empty() {
        AddUserRequest request = new AddUserRequest("", "12345678");
        List<CoreError> result = subject.validate(request);
        assertThat(result).isNotEmpty();
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Login") &&
                coreError.getMessage().equals("Must not be empty!"));
    }
}