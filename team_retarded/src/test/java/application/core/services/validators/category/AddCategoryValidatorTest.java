package application.core.services.validators.category;

import application.core.requests.category.AddCategoryRequest;
import application.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AddCategoryValidatorTest {

    private AddCategoryValidator subject;

    @BeforeEach
    void setUp() {
        subject = new AddCategoryValidator();
    }

    @Test
    void name_is_empty() {
        AddCategoryRequest request = new AddCategoryRequest("");
        List<CoreError> result = subject.validate(request);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Name") &&
                coreError.getMessage().equals("Must not be empty!"));
    }

    @Test
    void name_is_null() {
        AddCategoryRequest request = new AddCategoryRequest(null);
        List<CoreError> result = subject.validate(request);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Name") &&
                coreError.getMessage().equals("Must not be empty!"));
    }

    @Test
    void name_is_3_characters() {
        AddCategoryRequest request = new AddCategoryRequest("123");
        List<CoreError> result = subject.validate(request);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Name") &&
                coreError.getMessage().equals("Must be between 4 and 100 characters"));
    }

    @Test
    void name_is_4_characters() {
        AddCategoryRequest request = new AddCategoryRequest("1234");
        List<CoreError> result = subject.validate(request);
        assertThat(result).isEmpty();
    }

    @Test
    void name_is_101_characters() {
        AddCategoryRequest request = new AddCategoryRequest("111111111111111111111111111111111111111111111" +
                "11111111111111111111111111111111111111111111111111111111");
        List<CoreError> result = subject.validate(request);
        assertThat(result.size()).isEqualTo(1);
        assertThat(result).allMatch(coreError -> coreError.getField().equals("Name") &&
                coreError.getMessage().equals("Must be between 4 and 100 characters"));
    }

    @Test
    void name_is_100_characters() {
        AddCategoryRequest request = new AddCategoryRequest("111111111111111111111111111111111111111111111" +
                "1111111111111111111111111111111111111111111111111111111");
        List<CoreError> result = subject.validate(request);
        assertThat(result).isEmpty();
    }
}