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
        List<CoreError> result = subject.validate(new AddProductRequest("1111111111111111111111111111111111111111" +
                "111111111111111111112222222222333333333333333333344444444445", "description", 123.1));
        assertThat(result).isEmpty();
    }

    @Test
    void validate_name_is_101_characters() {
        List<CoreError> result = subject.validate(new AddProductRequest("1111111111111111111111111111111111111111" +
                "111111111111111111112222222222333333333333333333344444444445aAA"
                , "description", 5554.5));
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Name") &&
                coreError.getMessage().equals("Must be between 4 and 100 characters"));
    }

    @Test
    void validate_description() {
        List<CoreError> result = subject.validate(new AddProductRequest("Nevil", "description", 54.5));
        assertThat(result).isEmpty();

    }

    @Test
    void validate_description2() {
        List<CoreError> result = subject.validate(new AddProductRequest("Sony", "TV", 10000.0));
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Description") &&
                coreError.getMessage().equals("Must be between 10 and 2000 characters"));
    }

    @Test
    void validate_price() {
        List<CoreError> result = subject.validate(new AddProductRequest("Car0","MazdaMazda",1200.0));
        assertThat(result).isEmpty();
    }
    @Test
    void validate_price1() {
        List<CoreError> result = subject.validate(new AddProductRequest("Car0","MazdaMazda",1000000.0));
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Price") &&
                coreError.getMessage().equals("Must be between 0 and 100000"));
    }

    @Test
    void validate_priceAll() {
        List<CoreError> result = subject.validate(new AddProductRequest("Car","Mazda",1000000.0));
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Price") &&
                coreError.getMessage().equals("Must be between 0 and 100000") || coreError.getField().equals("Name") &&
                coreError.getMessage().equals("Must be between 4 and 100 characters") ||coreError.getField().equals("Description") &&
                coreError.getMessage().equals("Must be between 10 and 2000 characters"));
    }

}
