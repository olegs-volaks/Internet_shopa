package application.core.services.validators;

import application.core.requests.AddProductRequest;
import application.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AddProductValidatorTest {

    AddProductValidator subject;

    @BeforeEach
    void setUp() {
        subject = new AddProductValidator();
    }

    @Test
    void validate_name_is_3_characters() {
        List<CoreError> result = subject.validate(new AddProductRequest("123", "description", 123.1));
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Name") &&
                coreError.getMessage().equals("Must be between 4 and 100 characters"));
    }

    @Test
    void validate_name_is_4_characters() {
        List<CoreError> result = subject.validate(new AddProductRequest("1233", "description", 123.1));
        assertThat(result).isEmpty();
    }

    @Test
    void validate_name_is_100_characters() {
        List<CoreError> result = subject.validate(new AddProductRequest("1233", "description", 123.1));
        assertThat(result).isEmpty();
    }
}